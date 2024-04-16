package dev.ptit.ui.screen.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ptit.data.league.LeagueEntity
import dev.ptit.data.league.LeagueRepository
import dev.ptit.data.match.MatchEntity
import dev.ptit.data.match.MatchRepository
import dev.ptit.data.team.TeamEntity
import dev.ptit.data.team.TeamRepository
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
    val matches = _matches.asStateFlow()

    private val _teams = MutableStateFlow<List<TeamEntity>>(listOf())
    val teams = _teams.asStateFlow()

    init {
        viewModelScope.launch {
            leagueRepository.getAllLeagues().collect { leagueList ->
                _leagues.value = leagueList
            }
        }

        viewModelScope.launch {
            matchRepository.getAllMatches().collect { matchList ->
                _matches.value = matchList
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
    }

    fun onLeagueClick(leagueId: Int) {
        _selectedLeagueId.value = leagueId
    }
}