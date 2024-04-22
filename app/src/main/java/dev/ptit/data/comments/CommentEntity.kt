package dev.ptit.data.comments

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "comment", indices = [Index(value = ["remoteId"], unique = true)])
data class CommentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val remoteId : Int = 0,
    val comment : String = "",
    val userId : Int = 0,
    val matchId : Int = 0
)
