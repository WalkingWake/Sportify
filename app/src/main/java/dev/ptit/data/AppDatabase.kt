package dev.ptit.data

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.ptit.data.comments.CommentDao
import dev.ptit.data.comments.CommentEntity
import dev.ptit.data.goal.GoalDao
import dev.ptit.data.goal.GoalEntity
import dev.ptit.data.league.LeagueDao
import dev.ptit.data.league.LeagueEntity
import dev.ptit.data.leagueteammapping.LeagueTeamDao
import dev.ptit.data.leagueteammapping.LeagueTeamEntity
import dev.ptit.data.match.MatchDao
import dev.ptit.data.match.MatchEntity
import dev.ptit.data.matchdata.MatchDataDao
import dev.ptit.data.matchdata.MatchDataEntity
import dev.ptit.data.news.NewsDao
import dev.ptit.data.news.NewsEntity
import dev.ptit.data.newstagmapping.NewsTagDao
import dev.ptit.data.newstagmapping.NewsTagEntity
import dev.ptit.data.substitution.SubstitutionDao
import dev.ptit.data.substitution.SubstitutionEntity
import dev.ptit.data.tag.TagDao
import dev.ptit.data.tag.TagEntity
import dev.ptit.data.team.TeamDao
import dev.ptit.data.team.TeamEntity
import dev.ptit.data.yellowcard.YellowCardDao
import dev.ptit.data.yellowcard.YellowCardEntity

@Database(
    entities = [
        NewsEntity::class,
        TagEntity::class,
        NewsTagEntity::class,
        LeagueEntity::class,
        TeamEntity::class,
        LeagueTeamEntity::class,
        MatchEntity::class,
        CommentEntity::class,
        MatchDataEntity::class,
        YellowCardEntity::class,
        GoalEntity::class,
        SubstitutionEntity::class
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

    abstract fun commentDao(): CommentDao

    abstract fun matchDataDao(): MatchDataDao

    abstract fun yellowCardDao(): YellowCardDao

    abstract fun goalDao(): GoalDao

    abstract fun substitutionDao(): SubstitutionDao
}