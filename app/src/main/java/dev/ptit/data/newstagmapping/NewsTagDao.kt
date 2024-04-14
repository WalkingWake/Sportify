package dev.ptit.data.newstagmapping

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsTagDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsTag(newsTag: NewsTagEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsTags(newsTags: List<NewsTagEntity>)

    @Query("SELECT * FROM news_tag")
    fun getAllNewsTags(): Flow<List<NewsTagEntity>>
}