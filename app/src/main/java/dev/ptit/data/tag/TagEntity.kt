package dev.ptit.data.tag

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "tag", indices = [Index(value = ["remoteId"], unique = true)])
data class TagEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val remoteId : Int = 0,
    val tag : String = ""
)
