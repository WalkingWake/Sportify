package dev.ptit.data.league

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "league", indices = [Index(value = ["remoteId"], unique = true)])
data class LeagueEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val remoteId: Int = 0,
    val name: String = "",
    val logo: String = ""
)