package dev.ptit.data

import com.google.firebase.database.FirebaseDatabase
import dev.ptit.data.comments.CommentEntity
import dev.ptit.data.comments.CommentRepository
import dev.ptit.data.goal.GoalEntity
import dev.ptit.data.goal.GoalRepository
import dev.ptit.data.league.LeagueEntity
import dev.ptit.data.league.LeagueRepository
import dev.ptit.data.leagueteammapping.LeagueTeamEntity
import dev.ptit.data.leagueteammapping.LeagueTeamRepository
import dev.ptit.data.match.MatchEntity
import dev.ptit.data.match.MatchRepository
import dev.ptit.data.matchdata.MatchDataEntity
import dev.ptit.data.matchdata.MatchDataRepository
import dev.ptit.data.matchnewsmapping.MatchNewsEntity
import dev.ptit.data.matchnewsmapping.MatchNewsRepository
import dev.ptit.data.news.NewsEntity
import dev.ptit.data.news.NewsRepository
import dev.ptit.data.newstagmapping.NewsTagEntity
import dev.ptit.data.newstagmapping.NewsTagRepository
import dev.ptit.data.substitution.SubstitutionEntity
import dev.ptit.data.substitution.SubstitutionRepository
import dev.ptit.data.tag.TagEntity
import dev.ptit.data.tag.TagRepository
import dev.ptit.data.team.TeamEntity
import dev.ptit.data.team.TeamRepository
import dev.ptit.data.yellowcard.YellowCardEntity
import dev.ptit.data.yellowcard.YellowCardRepository
import dev.ptit.setup.extension.addValueEventListenerFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirebaseService(
    private val firebaseInstance: FirebaseDatabase,
    private val newsRepository: NewsRepository,
    private val tagRepository: TagRepository,
    private val newsTagRepository: NewsTagRepository,
    private val leagueRepository: LeagueRepository,
    private val teamRepository: TeamRepository,
    private val leagueTeamRepository: LeagueTeamRepository,
    private val matchRepository: MatchRepository,
    private val commentRepository: CommentRepository,
    private val goalRepository: GoalRepository,
    private val yellowCardRepository: YellowCardRepository,
    private val substitutionRepository: SubstitutionRepository,
    private val matchDataRepository: MatchDataRepository,
    private val matchNewsRepository: MatchNewsRepository
) {

    init {
        getAllNews()
        getAllTags()
        getAllNewsTag()
        getAllLeagues()
        getAllTeams()
        getAllLeagueTeams()
        getAllMatches()
        getAllComments()
        getAllGoals()
        getAllYellowCards()
        getAllSubstitutions()
        getAllMatchData()
        getAllMatchNews()
    }


    private fun getAllNews() {
        CoroutineScope(Dispatchers.IO).launch {
            firebaseInstance.getReference("news")
                .addValueEventListenerFlow(NewsEntity::class.java).collect { news ->
                    newsRepository.insertNews(news)
                }
        }
    }

    private fun getAllTags() {
        CoroutineScope(Dispatchers.IO).launch {
            firebaseInstance.getReference("tags")
                .addValueEventListenerFlow(TagEntity::class.java).collect { tags ->
                    tagRepository.insertTags(tags)
                }
        }
    }

    private fun getAllNewsTag() {
        CoroutineScope(Dispatchers.IO).launch {
            firebaseInstance.getReference("news_tag")
                .addValueEventListenerFlow(NewsTagEntity::class.java).collect { newsTags ->
                    newsTagRepository.insertNewsTags(newsTags)
                }
        }
    }

    private fun getAllLeagues() {
        CoroutineScope(Dispatchers.IO).launch {
            firebaseInstance.getReference("leagues")
                .addValueEventListenerFlow(LeagueEntity::class.java).collect { leagues ->
                    leagueRepository.insertLeagues(leagues)
                }
        }
    }

    private fun getAllTeams() {
        CoroutineScope(Dispatchers.IO).launch {
            firebaseInstance.getReference("teams")
                .addValueEventListenerFlow(TeamEntity::class.java).collect { teams ->
                    teamRepository.insertTeams(teams)
                }
        }
    }

    private fun getAllLeagueTeams() {
        CoroutineScope(Dispatchers.IO).launch {
            firebaseInstance.getReference("league_team")
                .addValueEventListenerFlow(LeagueTeamEntity::class.java).collect { leagueTeams ->
                    leagueTeamRepository.insertLeagueTeams(leagueTeams)
                }
        }
    }

    private fun getAllMatches() {
        CoroutineScope(Dispatchers.IO).launch {
            firebaseInstance.getReference("matches")
                .addValueEventListenerFlow(MatchEntity::class.java).collect { matches ->
                    matchRepository.insertMatches(matches)
                }
        }
    }

    private fun getAllComments(){
        CoroutineScope(Dispatchers.IO).launch {
            firebaseInstance.getReference("comments")
                .addValueEventListenerFlow(CommentEntity::class.java).collect { comments ->
                    commentRepository.insertComments(comments)
                }
        }
    }

    private fun getAllGoals(){
        CoroutineScope(Dispatchers.IO).launch {
            firebaseInstance.getReference("goals")
                .addValueEventListenerFlow(GoalEntity::class.java).collect { goals ->
                    goalRepository.insertGoals(goals)
                }
        }
    }

    private fun getAllYellowCards(){
        CoroutineScope(Dispatchers.IO).launch {
            firebaseInstance.getReference("yellowCards")
                .addValueEventListenerFlow(YellowCardEntity::class.java).collect { yellowCards ->
                    yellowCardRepository.insertYellowCards(yellowCards)
                }
        }
    }

    private fun getAllSubstitutions(){
        CoroutineScope(Dispatchers.IO).launch {
            firebaseInstance.getReference("substitutions")
                .addValueEventListenerFlow(SubstitutionEntity::class.java).collect { substitutions ->
                    substitutionRepository.insertSubstitutions(substitutions)
                }
        }
    }

    private fun getAllMatchData(){
        CoroutineScope(Dispatchers.IO).launch {
            firebaseInstance.getReference("matchDatas")
                .addValueEventListenerFlow(MatchDataEntity::class.java).collect { matchData ->
                    matchDataRepository.insertMatchData(matchData)
                }
        }
    }

    private fun getAllMatchNews(){
        CoroutineScope(Dispatchers.IO).launch {
            firebaseInstance.getReference("match_news")
                .addValueEventListenerFlow(MatchNewsEntity::class.java).collect { matchNews ->
                    matchNewsRepository.insertMatchNews(matchNews)
                }
        }
    }

}