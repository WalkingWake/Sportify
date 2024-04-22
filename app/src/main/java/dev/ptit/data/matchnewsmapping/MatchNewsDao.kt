package dev.ptit.data.matchnewsmapping

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchNewsDao {
    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertMatchNews(matchNewsEntity: MatchNewsEntity)

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertMatchNews(matchNewsEntities: List<MatchNewsEntity>)

    @Query("SELECT * FROM match_news")
    fun getAllMatchNews(): Flow<List<MatchNewsEntity>>
}