package dev.ptit.data.matchdata

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "match_data", indices = [Index(value = ["remoteId"], unique = true)])
data class MatchDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val remoteId : Int = 0,
    val matchId : Int = 0,
    val shots1 : Int = 0,
    val shots2 : Int = 0,
    val shotsOnTarget1 : Int = 0,
    val shotsOnTarget2 : Int = 0,
    val possession1 : Double = 0.0,
    val possession2 : Double = 0.0,
    val passes1 : Int = 0,
    val passes2 : Int = 0,
    val passAccuracy1 : Double = 0.0,
    val passAccuracy2 : Double = 0.0,
    val fouls1 : Int = 0,
    val fouls2 : Int = 0,
    val yellowCards1 : Int = 0,
    val yellowCards2 : Int = 0,
    val redCards1 : Int = 0,
    val redCards2 : Int = 0,
    val offsides1 : Int = 0,
    val offsides2 : Int = 0,
    val corners1 : Int = 0,
    val corners2 : Int = 0
)
