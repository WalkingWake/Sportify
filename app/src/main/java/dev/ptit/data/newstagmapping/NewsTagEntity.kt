package dev.ptit.data.newstagmapping

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_tag", indices = [androidx.room.Index(value = ["remoteId"], unique = true)])
data class NewsTagEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val remoteId : Int = 0,
    val newsId: Int = 0,
    val tagId: Int = 0
)