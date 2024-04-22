package dev.ptit.data.comments

class CommentRepository(
    private val commentDao: CommentDao
) {
    fun getAllComments() = commentDao.getAllComments()

    suspend fun insertComment(comment: CommentEntity) = commentDao.insertComment(comment)

    suspend fun insertComments(comments: List<CommentEntity>) = commentDao.insertComments(comments)
}