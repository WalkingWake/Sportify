package dev.ptit.data.matchnewsmapping

import androidx.room.PrimaryKey


@androidx.room.Entity(tableName = "match_news", indices = [androidx.room.Index(value = ["remoteId"], unique = true)])
data class MatchNewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val remoteId : Int = 0,
    val matchId : Int = 0,
    val newsId : Int = 0,
)
