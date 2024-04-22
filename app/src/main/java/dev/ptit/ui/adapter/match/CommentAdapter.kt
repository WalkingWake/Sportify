package dev.ptit.ui.adapter.match

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.ptit.data.comments.CommentEntity
import dev.ptit.data.user.UserModel
import dev.ptit.databinding.ItemRvCommentBinding

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private var comments = listOf<CommentEntity>()
    private var users = listOf<UserModel>()

    inner class CommentViewHolder(private val binding: ItemRvCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: CommentEntity) {
            Log.d("CommentAdapter", "bind: ${comment.comment}")
            binding.tvName.text = getUserById(comment.userId)?.name
            binding.tvComment.text = comment.comment
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
            ItemRvCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    private fun getUserById(userId: Int): UserModel? {
        return users.find { it.remoteId == userId }
    }
    fun setComments(comments: List<CommentEntity>) {
        this.comments = comments
        notifyDataSetChanged()
    }

    fun setUsers(users: List<UserModel>) {
        this.users = users
        notifyDataSetChanged()
    }
}