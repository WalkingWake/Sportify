package dev.ptit.data.matchdata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDataDao {
    @Query("SELECT * FROM match_data")
    fun getAllMatchData(): Flow<List<MatchDataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatchData(matchDataEntity: MatchDataEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatchData(matchDataEntities: List<MatchDataEntity>)
}