package dev.ptit.data.comments

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.first

class CommentService(
    private val firebaseInstance: FirebaseDatabase,
    private val commentRepository: CommentRepository
) {

    suspend fun addComment(comment: CommentEntity) {
        val comments = commentRepository.getAllComments()
        val nextId = comments.first().size + 1

        val commentReference = firebaseInstance.getReference("comments/$nextId")
        commentReference.setValue(comment.apply {
            remoteId = nextId
        })
    }
}