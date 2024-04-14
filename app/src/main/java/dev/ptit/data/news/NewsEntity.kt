package dev.ptit.data.news

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "news", indices = [Index(value = ["remoteId"], unique = true)])
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val remoteId: Int = 0,
    val title: String = "",
    val date: String = "",
    val description: String = "",
    val image: String = "",
)