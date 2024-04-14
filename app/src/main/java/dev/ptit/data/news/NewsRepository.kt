package dev.ptit.data.news

import kotlinx.coroutines.flow.Flow

class NewsRepository(
    private val newsDao: NewsDao
) {

    fun getAllNews(): Flow<List<NewsEntity>> = newsDao.getAllNews()

    suspend fun insertNews(news: NewsEntity) {
        newsDao.insertNews(news)
    }

    suspend fun insertNews(news: List<NewsEntity>) {
        newsDao.insertNews(news)
    }

    fun getAllNewsTag(): List<String> {
        return listOf("All", "Trending", "Premier League", "La Liga", "Messi", "Ronaldo")
    }
}