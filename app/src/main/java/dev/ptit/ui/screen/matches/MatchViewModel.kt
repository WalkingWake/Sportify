package dev.ptit.ui.screen.matches

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ptit.data.comments.CommentEntity
import dev.ptit.data.comments.CommentRepository
import dev.ptit.data.comments.CommentService
import dev.ptit.data.goal.GoalEntity
import dev.ptit.data.goal.GoalRepository
import dev.ptit.data.league.LeagueEntity
import dev.ptit.data.league.LeagueRepository
import dev.ptit.data.match.MatchEntity
import dev.ptit.data.match.MatchRepository
import dev.ptit.data.matchdata.MatchDataEntity
import dev.ptit.data.matchdata.MatchDataRepository
import dev.ptit.data.substitution.SubstitutionEntity
import dev.ptit.data.substitution.SubstitutionRepository
import dev.ptit.data.team.TeamEntity
import dev.ptit.data.team.TeamRepository
import dev.ptit.data.user.UserModel
import dev.ptit.data.user.UserRepository
import dev.ptit.data.yellowcard.YellowCardEntity
import dev.ptit.data.yellowcard.YellowCardRepository
import dev.ptit.setup.extension.Timeline
import dev.ptit.setup.extension.formattedDateToLong
import dev.ptit.setup.extension.toTimeline
import dev.ptit.setup.keys.Keys
import dev.ptit.setup.pref.LazyPref
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val leagueRepository: LeagueRepository,
    private val matchRepository: MatchRepository,
    private val teamRepository: TeamRepository,
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository,
    private val commentService: CommentService,
    private val lazyPref: LazyPref,
    private val matchDataRepository: MatchDataRepository,
    private val goalRepository: GoalRepository,
    private val yellowCardRepository: YellowCardRepository,
    private val substitutionRepository: SubstitutionRepository
) : ViewModel() {

    private val _leagues = MutableStateFlow<List<LeagueEntity>>(listOf())
    val leagues = _leagues.asStateFlow()

    private val _matches = MutableStateFlow<List<MatchEntity>>(listOf())

    private val _teams = MutableStateFlow<List<TeamEntity>>(listOf())
    val teams = _teams.asStateFlow()

    private val _uiMatches = MutableStateFlow<List<MatchEntity>>(listOf())
    val uiMatches = _uiMatches.asStateFlow()

    private val comments = MutableStateFlow<List<CommentEntity>>(listOf())
    private val matchDatas = MutableStateFlow<List<MatchDataEntity>>(listOf())
    private val goals = MutableStateFlow<List<GoalEntity>>(listOf())
    private val yellowCards = MutableStateFlow<List<YellowCardEntity>>(listOf())
    private val substitutions = MutableStateFlow<List<SubstitutionEntity>>(listOf())

    private val _uiComments = MutableStateFlow<List<CommentEntity>>(listOf())
    val uiComments = _uiComments.asStateFlow()

    private val _timelines = MutableStateFlow<List<Timeline>>(listOf())
    val timelines = _timelines.asStateFlow()

    private val currentMatchId = MutableStateFlow(0)

    private val _currentMatchData = MutableStateFlow(MatchDataEntity())
    val currentMatchData = _currentMatchData.asStateFlow()

    private val _currentMatch = MutableStateFlow(MatchEntity())
    val currentMatch = _currentMatch.asStateFlow()

    private val _team1 = MutableStateFlow(TeamEntity())
    val team1 = _team1.asStateFlow()

    private val _team2 = MutableStateFlow(TeamEntity())
    val team2 = _team2.asStateFlow()

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

        viewModelScope.launch {
            commentRepository.getAllComments().collect { commentList ->
                comments.value = commentList
                setUIComments()
            }
        }

        viewModelScope.launch {
            matchDataRepository.getAllMatchData().collect { matchDataList ->
                matchDatas.value = matchDataList
            }
        }

        viewModelScope.launch {
            goalRepository.getAllGoals().collect { goalList ->
                goals.value = goalList
            }
        }

        viewModelScope.launch {
            yellowCardRepository.getAllYellowCards().collect { yellowCardList ->
                yellowCards.value = yellowCardList
            }
        }

        viewModelScope.launch {
            substitutionRepository.getAllSubstitutions().collect { substitutionList ->
                substitutions.value = substitutionList
            }
        }

    }

    fun getAllUsers(): List<UserModel> {
        return userRepository.getAllUsers()
    }

    private val _isUpcomingState = MutableStateFlow(true)
    val isUpcomingState = _isUpcomingState.asStateFlow()

    private val _selectedLeagueId = MutableStateFlow(0)
    val selectedLeagueId = _selectedLeagueId.asStateFlow()

    fun onUpcomingClick(isUpcoming: Boolean) {
        _isUpcomingState.value = isUpcoming
        setUIMatches()
    }

    private fun setUIComments() {
        _uiComments.value = comments.value.filter {
            it.matchId == currentMatchId.value
        }
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

    fun setCurrentMatchId(matchId: Int) {
        currentMatchId.value = matchId
        setUIComments()
        _currentMatchData.value =
            matchDatas.value.find { it.matchId == matchId } ?: MatchDataEntity()

        val match = _matches.value.find { it.remoteId == matchId }
        _team1.value = _teams.value.find { it.remoteId == match?.team1Id } ?: TeamEntity()
        _team2.value = _teams.value.find { it.remoteId == match?.team2Id } ?: TeamEntity()
        _currentMatch.value = match ?: MatchEntity()

        val timelineList = mutableListOf<Timeline>()
        goals.value.filter { it.matchId == matchId }.forEach {
            timelineList.add(it.toTimeline())
        }
        yellowCards.value.filter { it.matchId == matchId }.forEach {
            timelineList.add(it.toTimeline())
        }
        substitutions.value.filter { it.matchId == matchId }.forEach {
            timelineList.add(it.toTimeline())
        }
        _timelines.value = timelineList.sortedBy { it.time }
    }

    fun addComment(comment: CommentEntity) {
        viewModelScope.launch {
            commentService.addComment(comment.apply {
                userId = lazyPref.get(Keys.USER_ID, 0)
                matchId = currentMatchId.value
                Log.d("MatchViewModel", "addComment: $this")
            })
        }
    }
}