package dev.ptit.data

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.ptit.data.news.NewsDao
import dev.ptit.data.news.NewsEntity
import dev.ptit.data.newstagmapping.NewsTagDao
import dev.ptit.data.newstagmapping.NewsTagEntity
import dev.ptit.data.tag.TagDao
import dev.ptit.data.tag.TagEntity

@Database(entities = [NewsEntity::class, TagEntity::class, NewsTagEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao() : NewsDao

    abstract fun tagDao() : TagDao

    abstract fun newsTagDao() : NewsTagDao
}