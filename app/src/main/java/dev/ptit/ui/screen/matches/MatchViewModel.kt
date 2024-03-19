package dev.ptit.ui.screen.matches

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor() : ViewModel() {
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