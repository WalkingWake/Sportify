package dev.ptit.data.leagueteammapping

import androidx.room.Index
import androidx.room.PrimaryKey

@androidx.room.Entity(
    tableName = "league_team",
    indices = [Index(value = ["remoteId"], unique = true)]
)
data class LeagueTeamEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val leagueId: Int = 0,
    val teamId: Int = 0,
    val remoteId: Int = 0
)