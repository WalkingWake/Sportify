package dev.ptit.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ptit.data.league.LeagueEntity
import dev.ptit.data.league.LeagueRepository
import dev.ptit.data.match.MatchEntity
import dev.ptit.data.match.MatchRepository
import dev.ptit.data.news.NewsEntity
import dev.ptit.data.news.NewsRepository
import dev.ptit.data.tag.TagEntity
import dev.ptit.data.tag.TagRepository
import dev.ptit.data.team.TeamEntity
import dev.ptit.data.team.TeamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val leagueRepository: LeagueRepository,
    private val matchRepository: MatchRepository,
    private val teamRepository: TeamRepository
) : ViewModel() {

    private val _news = MutableStateFlow<List<NewsEntity>>(listOf())
    val news = _news.asStateFlow()

    private val _leagues = MutableStateFlow<List<LeagueEntity>>(listOf())
    val leagues = _leagues.asStateFlow()

    private val _matches = MutableStateFlow<List<MatchEntity>>(listOf())
    val matches = _matches.asStateFlow()

    private val _teams = MutableStateFlow<List<TeamEntity>>(listOf())
    val teams = _teams.asStateFlow()

    init {
        viewModelScope.launch {
            newsRepository.getAllNews().collect { newsList ->
                _news.update { newsList }
            }
        }

        viewModelScope.launch {
            leagueRepository.getAllLeagues().collect { leagueList ->
                _leagues.update { leagueList }
            }
        }

        viewModelScope.launch {
            matchRepository.getAllMatches().collect { matchList ->
                _matches.update { matchList }
            }
        }

        viewModelScope.launch {
            teamRepository.getAllTeams().collect { teamList ->
                _teams.update { teamList }
            }
        }

    }
}