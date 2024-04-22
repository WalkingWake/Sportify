package dev.ptit.data.substitution

import androidx.room.PrimaryKey

@androidx.room.Entity(tableName = "substitution", indices = [androidx.room.Index(value = ["remoteId"], unique = true)])
data class SubstitutionEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val remoteId : Int = 0,
    val matchId : Int = 0,
    val time : Int = 0,
    val side : Int = 0,
    val inPlayer : String = "",
    val outPlayer : String = ""
)
