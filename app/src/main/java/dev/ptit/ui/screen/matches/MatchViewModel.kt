package dev.ptit.ui.screen.matches

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ptit.data.league.LeagueEntity
import dev.ptit.data.league.LeagueRepository
import dev.ptit.data.match.MatchEntity
import dev.ptit.data.match.MatchRepository
import dev.ptit.data.team.TeamEntity
import dev.ptit.data.team.TeamRepository
import dev.ptit.setup.extension.formattedDateToLong
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val leagueRepository: LeagueRepository,
    private val matchRepository: MatchRepository,
    private val teamRepository: TeamRepository
) : ViewModel() {

    private val _leagues = MutableStateFlow<List<LeagueEntity>>(listOf())
    val leagues = _leagues.asStateFlow()

    private val _matches = MutableStateFlow<List<MatchEntity>>(listOf())

    private val _teams = MutableStateFlow<List<TeamEntity>>(listOf())
    val teams = _teams.asStateFlow()

    private val _uiMatches = MutableStateFlow<List<MatchEntity>>(listOf())
    val uiMatches = _uiMatches.asStateFlow()

    init {
        viewModelScope.launch {
            leagueRepository.getAllLeagues().collect { leagueList ->
                _leagues.value = leagueList
            }
        }

        viewModelScope.launch {
            matchRepository.getAllMatches().collect { matchList ->
                _matches.value = matchList
                setUIMatches()
            }
        }

        viewModelScope.launch {
            teamRepository.getAllTeams().collect { teamList ->
                _teams.value = teamList
            }
        }
    }

    private val _isUpcomingState = MutableStateFlow(true)
    val isUpcomingState = _isUpcomingState.asStateFlow()

    private val _selectedLeagueId = MutableStateFlow(0)
    val selectedLeagueId = _selectedLeagueId.asStateFlow()

    fun onUpcomingClick(isUpcoming: Boolean) {
        _isUpcomingState.value = isUpcoming
        setUIMatches()
    }

    private fun setUIMatches() {
        _uiMatches.value = if (_isUpcomingState.value) {
            _matches.value
                .filter { match ->
                    val currentTime = System.currentTimeMillis()
                    val matchTime = match.startTime.formattedDateToLong()
                    matchTime > currentTime
                }
                .sortedBy {
                    it.startTime.formattedDateToLong()
                }
        } else {
            _matches.value
                .filter { match ->
                    val currentTime = System.currentTimeMillis()
                    val matchTime = match.startTime.formattedDateToLong()
                    matchTime <= currentTime
                }
                .sortedBy {
                    -it.startTime.formattedDateToLong()
                }
        }

        _uiMatches.value = _uiMatches.value.filter { match ->
            match.leagueId == _selectedLeagueId.value
        }
    }

    fun onLeagueClick(leagueId: Int) {
        Log.d("TAG", "onLeagueClick: $leagueId")
        _selectedLeagueId.value = leagueId
        setUIMatches()
    }
}