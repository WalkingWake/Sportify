package dev.ptit.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dev.ptit.data.league.LeagueEntity
import dev.ptit.data.league.LeagueRepository
import dev.ptit.data.leagueteammapping.LeagueTeamEntity
import dev.ptit.data.leagueteammapping.LeagueTeamRepository
import dev.ptit.data.match.MatchEntity
import dev.ptit.data.match.MatchRepository
import dev.ptit.data.news.NewsEntity
import dev.ptit.data.news.NewsRepository
import dev.ptit.data.newstagmapping.NewsTagEntity
import dev.ptit.data.newstagmapping.NewsTagRepository
import dev.ptit.data.tag.TagEntity
import dev.ptit.data.tag.TagRepository
import dev.ptit.data.team.TeamEntity
import dev.ptit.data.team.TeamRepository
import dev.ptit.setup.extension.addValueEventListenerFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class FirebaseService(
    private val firebaseInstance: FirebaseDatabase,
    private val newsRepository: NewsRepository,
    private val tagRepository: TagRepository,
    private val newsTagRepository: NewsTagRepository,
    private val leagueRepository: LeagueRepository,
    private val teamRepository: TeamRepository,
    private val leagueTeamRepository: LeagueTeamRepository,
    private val matchRepository: MatchRepository
) {

    init {
        getAllNews()
        getAllTags()
        getAllNewsTag()
        getAllLeagues()
        getAllTeams()
        getAllLeagueTeams()
        getAllMatches()
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

}