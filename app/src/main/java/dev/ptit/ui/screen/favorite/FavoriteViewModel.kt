package dev.ptit.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ptit.data.league.LeagueEntity
import dev.ptit.data.league.LeagueRepository
import dev.ptit.data.leagueteammapping.LeagueTeamEntity
import dev.ptit.data.leagueteammapping.LeagueTeamRepository
import dev.ptit.data.team.TeamEntity
import dev.ptit.data.team.TeamRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val leagueRepository: LeagueRepository,
    private val teamRepository: TeamRepository,
    private val leagueTeamRepository: LeagueTeamRepository
) : ViewModel() {

    private val _selectedLeague = MutableStateFlow<LeagueEntity?>(null)
    val selectedLeague = _selectedLeague.asStateFlow()

    private val _teams = MutableStateFlow<List<TeamEntity>>(listOf())
    val teams = _teams.asStateFlow()

    private val _leagues = MutableStateFlow<List<LeagueEntity>>(listOf())
    val leagues = _leagues.asStateFlow()

    private val allTeams = MutableStateFlow<List<TeamEntity>>(listOf())

    private val allLeagueTeams = MutableStateFlow<List<LeagueTeamEntity>>(listOf())

    init {
        viewModelScope.launch {
            leagueRepository.getAllLeagues().collect { leagueList ->
                _leagues.value = leagueList
                updateUIState()
            }
        }

        viewModelScope.launch {
            teamRepository.getAllTeams().collect { teamList ->
                allTeams.value = teamList
            }
        }

        viewModelScope.launch {
            leagueTeamRepository.getAllLeagueTeams().collect { leagueTeamList ->
                allLeagueTeams.value = leagueTeamList
            }
        }
    }

    fun setSelectedLeague(league: LeagueEntity) {
        _selectedLeague.value = league
        updateUIState()
    }

    private fun updateUIState() {
        _selectedLeague.value?.let { league ->
            _teams.update {
                getTeamsByLeagueId(league.remoteId)
            }
        }
    }

    private fun getTeamsByLeagueId(leagueId: Int): List<TeamEntity> {
        val teams = mutableListOf<TeamEntity>()

        allLeagueTeams.value.forEach { leagueTeam ->
            if (leagueTeam.leagueId == leagueId) {
                val team = allTeams.value.find { it.remoteId == leagueTeam.teamId }
                team?.let { teams.add(it) }
            }
        }

        return teams
    }
}