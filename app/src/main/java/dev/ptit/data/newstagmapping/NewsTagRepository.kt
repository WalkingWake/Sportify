package dev.ptit.data.newstagmapping

import kotlinx.coroutines.flow.Flow

class NewsTagRepository(
    private val newsTagDao: NewsTagDao
) {

    suspend fun insertNewsTag(newsTag: NewsTagEntity) {
        newsTagDao.insertNewsTag(newsTag)
    }

    suspend fun insertNewsTags(newsTags: List<NewsTagEntity>) {
        newsTagDao.insertNewsTags(newsTags)
    }

    fun getAllNewsTags(): Flow<List<NewsTagEntity>> {
        return newsTagDao.getAllNewsTags()
    }
}