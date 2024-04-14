package dev.ptit.ui.screen.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ptit.data.league.LeagueEntity
import dev.ptit.data.league.LeagueRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val leagueRepository: LeagueRepository
) : ViewModel() {

    private val _leagues = MutableStateFlow<List<LeagueEntity>>(listOf())
    val leagues = _leagues.asStateFlow()

    init {
        viewModelScope.launch {
            leagueRepository.getAllLeagues().collect { leagueList ->
                _leagues.value = leagueList
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