package dev.ptit.data.league

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LeagueDao {

    @Query("SELECT * FROM league")
    fun getAllLeagues(): Flow<List<LeagueEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeague(league: LeagueEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeagues(leagues: List<LeagueEntity>)
}