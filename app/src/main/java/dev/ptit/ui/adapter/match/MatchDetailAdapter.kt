package dev.ptit.ui.adapter.match

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.ptit.databinding.ItemVpCommentMatchBinding
import dev.ptit.databinding.ItemVpLineupsMatchBinding
import dev.ptit.databinding.ItemVpNewsMatchBinding
import dev.ptit.databinding.ItemVpStatsMatchBinding
import dev.ptit.databinding.ItemVpTimelineMatchBinding

class MatchDetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class TimelineViewHolder(private val binding: ItemVpTimelineMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

    inner class StatsViewHolder(private val binding: ItemVpStatsMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

    inner class LineupsViewHolder(private val binding: ItemVpLineupsMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

    inner class NewsViewHolder(private val binding: ItemVpNewsMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

    inner class CommentViewHolder(private val binding: ItemVpCommentMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MatchDetailViewType.TIMELINE.type -> TimelineViewHolder(
                ItemVpTimelineMatchBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            MatchDetailViewType.STATS.type -> StatsViewHolder(
                ItemVpStatsMatchBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            MatchDetailViewType.LINEUPS.type -> LineupsViewHolder(
                ItemVpLineupsMatchBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            MatchDetailViewType.NEWS.type -> NewsViewHolder(
                ItemVpNewsMatchBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            MatchDetailViewType.COMMENT.type -> CommentViewHolder(
                ItemVpCommentMatchBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return MatchDetailViewType.entries.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TimelineViewHolder -> holder.bind()
            is StatsViewHolder -> holder.bind()
            is LineupsViewHolder -> holder.bind()
            is NewsViewHolder -> holder.bind()
            is CommentViewHolder -> holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return MatchDetailViewType.entries[position].type
    }
}