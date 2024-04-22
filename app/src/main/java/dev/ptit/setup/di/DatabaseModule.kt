package dev.ptit.setup.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.ptit.data.AppDatabase
import dev.ptit.data.comments.CommentDao
import dev.ptit.data.comments.CommentRepository
import dev.ptit.data.goal.GoalDao
import dev.ptit.data.goal.GoalRepository
import dev.ptit.data.league.LeagueDao
import dev.ptit.data.league.LeagueRepository
import dev.ptit.data.leagueteammapping.LeagueTeamDao
import dev.ptit.data.leagueteammapping.LeagueTeamRepository
import dev.ptit.data.match.MatchDao
import dev.ptit.data.match.MatchRepository
import dev.ptit.data.matchdata.MatchDataDao
import dev.ptit.data.matchdata.MatchDataRepository
import dev.ptit.data.matchnewsmapping.MatchNewsDao
import dev.ptit.data.matchnewsmapping.MatchNewsRepository
import dev.ptit.data.news.NewsDao
import dev.ptit.data.news.NewsRepository
import dev.ptit.data.newstagmapping.NewsTagDao
import dev.ptit.data.newstagmapping.NewsTagRepository
import dev.ptit.data.substitution.SubstitutionDao
import dev.ptit.data.substitution.SubstitutionRepository
import dev.ptit.data.tag.TagDao
import dev.ptit.data.tag.TagRepository
import dev.ptit.data.team.TeamDao
import dev.ptit.data.team.TeamRepository
import dev.ptit.data.yellowcard.YellowCardDao
import dev.ptit.data.yellowcard.YellowCardRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(database: AppDatabase) = database.newsDao()

    @Provides
    @Singleton
    fun provideNewsRepository(newsDao: NewsDao): NewsRepository {
        return NewsRepository(newsDao)
    }

    @Provides
    @Singleton
    fun provideTagDao(database: AppDatabase) = database.tagDao()

    @Provides
    @Singleton
    fun provideTagRepository(tagDao: TagDao): TagRepository {
        return TagRepository(tagDao)
    }

    @Provides
    @Singleton
    fun provideNewsTagDao(database: AppDatabase) = database.newsTagDao()

    @Provides
    @Singleton
    fun provideNewsTagRepository(newsTagDao: NewsTagDao): NewsTagRepository {
        return NewsTagRepository(newsTagDao)
    }

    @Provides
    @Singleton
    fun provideLeagueDao(database: AppDatabase) = database.leagueDao()

    @Provides
    @Singleton
    fun provideLeagueRepository(leagueDao: LeagueDao): LeagueRepository {
        return LeagueRepository(leagueDao)
    }

    @Provides
    @Singleton
    fun provideTeamDao(database: AppDatabase) = database.teamDao()

    @Provides
    @Singleton
    fun provideTeamRepository(teamDao: TeamDao): TeamRepository {
        return TeamRepository(teamDao)
    }

    @Provides
    @Singleton
    fun provideLeagueTeamDao(database: AppDatabase) = database.leagueTeamDao()

    @Provides
    @Singleton
    fun provideLeagueTeamRepository(leagueTeamDao: LeagueTeamDao): LeagueTeamRepository {
        return LeagueTeamRepository(leagueTeamDao)
    }

    @Provides
    @Singleton
    fun provideMatchDao(database: AppDatabase) = database.matchDao()

    @Provides
    @Singleton
    fun provideMatchRepository(matchDao: MatchDao): MatchRepository {
        return MatchRepository(matchDao)
    }

    @Provides
    @Singleton
    fun provideCommentDao(database: AppDatabase) = database.commentDao()

    @Provides
    @Singleton
    fun provideCommentRepository(commentDao: CommentDao): CommentRepository {
        return CommentRepository(commentDao)
    }

    @Provides
    @Singleton
    fun provideMatchDataDao(database: AppDatabase) = database.matchDataDao()

    @Provides
    @Singleton
    fun provideMatchDataRepository(matchDataDao: MatchDataDao): MatchDataRepository {
        return MatchDataRepository(matchDataDao)
    }

    @Provides
    @Singleton
    fun provideYellowCardDao(database: AppDatabase) = database.yellowCardDao()

    @Provides
    @Singleton
    fun provideYellowCardRepository(yellowCardDao: YellowCardDao): YellowCardRepository {
        return YellowCardRepository(yellowCardDao)
    }

    @Provides
    @Singleton
    fun provideSubstitutionDao(database: AppDatabase) = database.substitutionDao()

    @Provides
    @Singleton
    fun provideSubstitutionRepository(substitutionDao: SubstitutionDao): SubstitutionRepository {
        return SubstitutionRepository(substitutionDao)
    }

    @Provides
    @Singleton
    fun provideGoalDao(database: AppDatabase) = database.goalDao()

    @Provides
    @Singleton
    fun provideGoalRepository(goalDao: GoalDao): GoalRepository {
        return GoalRepository(goalDao)
    }

    @Provides
    @Singleton
    fun provideMatchNewsDao(database: AppDatabase) = database.matchNewsDao()

    @Provides
    @Singleton
    fun provideMatchNewsRepository(matchNewsDao: MatchNewsDao): MatchNewsRepository {
        return MatchNewsRepository(matchNewsDao)
    }
}