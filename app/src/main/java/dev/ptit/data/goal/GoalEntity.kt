package dev.ptit.data.goal

import androidx.room.PrimaryKey


@androidx.room.Entity(tableName = "goal", indices = [androidx.room.Index(value = ["remoteId"], unique = true)])
data class GoalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val remoteId: Int = 0,
    val matchId: Int = 0,
    val time: Int = 0,
    val side: Int = 0,
    val name: String = "",
    val score : String = ""
)
