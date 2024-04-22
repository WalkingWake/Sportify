package dev.ptit.data.yellowcard

import androidx.room.PrimaryKey

@androidx.room.Entity(tableName = "yellow_card", indices = [androidx.room.Index(value = ["remoteId"], unique = true)])
data class YellowCardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val remoteId: Int = 0,
    val matchId: Int = 0,
    val time: Int = 0,
    val side: Int = 0,
    val name: String = "",
)
