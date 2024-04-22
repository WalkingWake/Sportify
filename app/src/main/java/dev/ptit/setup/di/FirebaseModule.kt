package dev.ptit.setup.di

import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ptit.data.FirebaseService
import dev.ptit.data.comments.CommentRepository
import dev.ptit.data.comments.CommentService
import dev.ptit.data.goal.GoalRepository
import dev.ptit.data.league.LeagueRepository
import dev.ptit.data.leagueteammapping.LeagueTeamEntity
import dev.ptit.data.leagueteammapping.LeagueTeamRepository
import dev.ptit.data.match.MatchRepository
import dev.ptit.data.matchdata.MatchDataRepository
import dev.ptit.data.matchnewsmapping.MatchNewsRepository
import dev.ptit.data.news.NewsRepository
import dev.ptit.data.newstagmapping.NewsTagRepository
import dev.ptit.data.substitution.SubstitutionRepository
import dev.ptit.data.tag.TagRepository
import dev.ptit.data.team.TeamRepository
import dev.ptit.data.user.UserRepository
import dev.ptit.data.yellowcard.YellowCardRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideUserRepository(firebaseDatabase: FirebaseDatabase): UserRepository {
        return UserRepository(firebaseDatabase)
    }

    @Provides
    @Singleton
    fun provideCommentService(
        firebaseDatabase: FirebaseDatabase,
        commentRepository: CommentRepository
    ): CommentService {
        return CommentService(firebaseDatabase, commentRepository)
    }

    @Provides
    @Singleton
    fun provideFirebaseService(
        firebaseDatabase: FirebaseDatabase,
        newsRepository: NewsRepository,
        tagRepository: TagRepository,
        newsTagRepository: NewsTagRepository,
        leagueRepository: LeagueRepository,
        teamRepository: TeamRepository,
        leagueTeamRepository: LeagueTeamRepository,
        matchRepository: MatchRepository,
        commentRepository: CommentRepository,
        goalRepository: GoalRepository,
        yellowCardRepository: YellowCardRepository,
        substitutionRepository: SubstitutionRepository,
        matchDataRepository: MatchDataRepository,
        matchNewsRepository: MatchNewsRepository
    ): FirebaseService {
        return FirebaseService(
            firebaseDatabase,
            newsRepository,
            tagRepository,
            newsTagRepository,
            leagueRepository,
            teamRepository,
            leagueTeamRepository,
            matchRepository,
            commentRepository,
            goalRepository,
            yellowCardRepository,
            substitutionRepository,
            matchDataRepository,
            matchNewsRepository
        )
    }
}