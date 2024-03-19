package dev.ptit.data.news

class NewsRepository {

    fun getAllNews(): List<NewsModel> {
        return listOf(
            NewsModel(
                1,
                "Qatar World Cup 2022",
                "Messi goals against Switzerland",
                "",
                ""
            ),
            NewsModel(
                2,
                "Qatar World Cup 2022",
                "Messi goals against Switzerland",
                "",
                ""
            ),
            NewsModel(
                3,
                "Qatar World Cup 2022",
                "Messi goals against Switzerland",
                "",
                ""
            ),
            NewsModel(
                4,
                "Qatar World Cup 2022",
                "Messi goals against Switzerland",
                "",
                ""
            ),
            NewsModel(
                5,
                "Qatar World Cup 2022",
                "Messi goals against Switzerland",
                "",
                ""
            ),
            NewsModel(
                6,
                "Qatar World Cup 2022",
                "Messi goals against Switzerland",
                "",
                ""
            ),
        )
    }

    fun getAllNewsTag(): List<String> {
        return listOf("All", "Trending", "Premier League", "La Liga", "Messi", "Ronaldo")
    }
}