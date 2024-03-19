package dev.ptit.data.match

class MatchRepository {

    fun getAllMatches(): List<MatchModel> {
        return listOf(
            MatchModel(
                1,
                "MU",
                "MU",
                1, 3, "", "", false
            ),
            MatchModel(
                1,
                "MU",
                "MU",
                1, 3, "", "", true
            ),
            MatchModel(
                1,
                "MU",
                "MU",
                1, 3, "", "", false
            ),
            MatchModel(
                1,
                "MU",
                "MU",
                1, 3, "", "", true
            ),
            MatchModel(
                1,
                "MU",
                "MU",
                1, 3, "", "", false
            ),
            MatchModel(
                1,
                "MU",
                "MU",
                1, 3, "", "", true
            ),
            MatchModel(
                1,
                "MU",
                "MU",
                1, 3, "", "", false
            ),
            MatchModel(
                1,
                "MU",
                "MU",
                1, 3, "", "", true
            ),
            MatchModel(
                1,
                "MU",
                "MU",
                1, 3, "", "", false
            ),
            MatchModel(
                1,
                "MU",
                "MU",
                1, 3, "", "", false
            ),
            MatchModel(
                1,
                "MU",
                "MU",
                1, 3, "", "", false
            ),
            MatchModel(
                1,
                "MU",
                "MU",
                1, 3, "", "", false
            ),
            MatchModel(
                1,
                "MU",
                "MU",
                1, 3, "", "", false
            ),
            MatchModel(
                1,
                "MU",
                "MU",
                1, 3, "", "", false
            ),
        )
    }

    fun getUpcomingMatches(): List<MatchModel> {
        return getAllMatches().filter { it.isUpcoming }
    }

    fun getPastMatches(): List<MatchModel> {
        return getAllMatches().filter { !it.isUpcoming }
    }
}