package dev.ptit.data

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.ptit.data.league.LeagueDao
import dev.ptit.data.league.LeagueEntity
import dev.ptit.data.leagueteammapping.LeagueTeamDao
import dev.ptit.data.leagueteammapping.LeagueTeamEntity
import dev.ptit.data.match.MatchDao
import dev.ptit.data.match.MatchEntity
import dev.ptit.data.news.NewsDao
import dev.ptit.data.news.NewsEntity
import dev.ptit.data.newstagmapping.NewsTagDao
import dev.ptit.data.newstagmapping.NewsTagEntity
import dev.ptit.data.tag.TagDao
import dev.ptit.data.tag.TagEntity
import dev.ptit.data.team.TeamDao
import dev.ptit.data.team.TeamEntity

@Database(
    entities = [
        NewsEntity::class,
        TagEntity::class,
        NewsTagEntity::class,
        LeagueEntity::class,
        TeamEntity::class,
        LeagueTeamEntity::class,
        MatchEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    abstract fun tagDao(): TagDao

    abstract fun newsTagDao(): NewsTagDao

    abstract fun leagueDao(): LeagueDao

    abstract fun teamDao(): TeamDao

    abstract fun leagueTeamDao(): LeagueTeamDao

    abstract fun matchDao(): MatchDao
}