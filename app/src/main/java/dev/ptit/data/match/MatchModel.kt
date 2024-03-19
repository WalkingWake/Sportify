package dev.ptit.data.match

data class MatchModel(
    val id: Int,
    val homeTeam: String,
    val awayTeam: String,
    val homeScore: Int,
    val awayScore: Int,
    val time: String,
    val league: String,
    val isUpcoming: Boolean
)
