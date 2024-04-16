package dev.ptit.data.match

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "match", indices = [Index(value = ["remoteId"], unique = true)])
data class MatchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val remoteId: Int = 0,
    val startTime: String = "",
    val team1Id: Int = 0,
    val team2Id: Int = 0,
    val team1Score: Int = 0,
    val team2Score: Int = 0,
    val leagueId: Int = 0,
)
