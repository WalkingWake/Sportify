package dev.ptit.setup.di

import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ptit.data.FirebaseService
import dev.ptit.data.news.NewsRepository
import dev.ptit.data.newstagmapping.NewsTagRepository
import dev.ptit.data.tag.TagRepository
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
    fun provideFirebaseService(
        firebaseDatabase: FirebaseDatabase,
        newsRepository: NewsRepository,
        tagRepository: TagRepository,
        newsTagRepository: NewsTagRepository
    ): FirebaseService {
        return FirebaseService(firebaseDatabase, newsRepository, tagRepository, newsTagRepository)
    }
}