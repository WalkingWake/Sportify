package dev.ptit.data.team

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team", indices = [androidx.room.Index(value = ["remoteId"], unique = true)])
data class TeamEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val remoteId: Int = 0,
    val name: String = "",
    val logo: String = ""
)