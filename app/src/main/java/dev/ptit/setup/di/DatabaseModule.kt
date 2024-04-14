package dev.ptit.setup.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.ptit.data.AppDatabase
import dev.ptit.data.league.LeagueDao
import dev.ptit.data.league.LeagueRepository
import dev.ptit.data.leagueteammapping.LeagueTeamDao
import dev.ptit.data.leagueteammapping.LeagueTeamRepository
import dev.ptit.data.news.NewsDao
import dev.ptit.data.news.NewsRepository
import dev.ptit.data.newstagmapping.NewsTagDao
import dev.ptit.data.newstagmapping.NewsTagRepository
import dev.ptit.data.tag.TagDao
import dev.ptit.data.tag.TagRepository
import dev.ptit.data.team.TeamDao
import dev.ptit.data.team.TeamRepository
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
}