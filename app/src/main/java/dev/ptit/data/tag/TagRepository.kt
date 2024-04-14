package dev.ptit.data.tag

import kotlinx.coroutines.flow.Flow

class TagRepository(
    private val tagDao: TagDao
) {

    fun getAllTags(): Flow<List<TagEntity>> = tagDao.getAllTags()

    suspend fun insertTag(tag: TagEntity) {
        tagDao.insertTag(tag)
    }

    suspend fun insertTags(tags: List<TagEntity>) {
        tagDao.insertTags(tags)
    }

    fun getTagAll() : TagEntity {
        return TagEntity(
            id = -1,
            tag = "All",
            remoteId = -1
        )
    }
}