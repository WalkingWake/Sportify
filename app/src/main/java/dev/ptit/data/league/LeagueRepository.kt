package dev.ptit.data.league

class LeagueRepository {

    fun getAllLeagues() : List<LeagueModel>{
        return listOf(
            LeagueModel(
                0,
                "Premier League",
                "https://www.thesportsdb.com/images/media/league/badge/8v6l7h1572386727.png",
            ),
            LeagueModel(
                1,
                "Premier League",
                "https://www.thesportsdb.com/images/media/league/badge/8v6l7h1572386727.png",
            ),
            LeagueModel(
                2,
                "Premier League",
                "https://www.thesportsdb.com/images/media/league/badge/8v6l7h1572386727.png",
            ),
            LeagueModel(
                3,
                "Premier League",
                "https://www.thesportsdb.com/images/media/league/badge/8v6l7h1572386727.png",
            ),
            LeagueModel(
                4,
                "Premier League",
                "https://www.thesportsdb.com/images/media/league/badge/8v6l7h1572386727.png",
            ),
            LeagueModel(
                5,
                "Premier League",
                "https://www.thesportsdb.com/images/media/league/badge/8v6l7h1572386727.png",
            ),
            LeagueModel(
                6,
                "Premier League",
                "https://www.thesportsdb.com/images/media/league/badge/8v6l7h1572386727.png",
            ),
        )
    }
}