package dev.ptit.data.leagueteammapping

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LeagueTeamDao {

    @Query("SELECT * FROM league_team")
    fun getAllLeagueTeams(): Flow<List<LeagueTeamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeagueTeam(leagueTeam: LeagueTeamEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeagueTeams(leagueTeams: List<LeagueTeamEntity>)
}