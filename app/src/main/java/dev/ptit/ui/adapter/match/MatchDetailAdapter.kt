package dev.ptit.ui.adapter.match

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import dev.ptit.data.comments.CommentEntity
import dev.ptit.data.matchdata.MatchDataEntity
import dev.ptit.data.news.NewsEntity
import dev.ptit.data.news.NewsRepository
import dev.ptit.data.user.UserModel
import dev.ptit.databinding.ItemVpCommentMatchBinding
import dev.ptit.databinding.ItemVpLineupsMatchBinding
import dev.ptit.databinding.ItemVpNewsMatchBinding
import dev.ptit.databinding.ItemVpStatsMatchBinding
import dev.ptit.databinding.ItemVpTimelineMatchBinding
import dev.ptit.setup.extension.Timeline
import dev.ptit.ui.adapter.news.NewsRVAdapter

class MatchDetailAdapter(
    private val addComment : (String) -> Unit = {  },
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val commentAdapter = CommentAdapter()
    private var matchData: MatchDataEntity = MatchDataEntity()
    private val timelineAdapter = TimelineAdapter()
    private val newsAdapter = NewsRVAdapter {}


    inner class TimelineViewHolder(private val binding: ItemVpTimelineMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.rvTimeline.adapter = timelineAdapter
        }
    }

    inner class StatsViewHolder(private val binding: ItemVpStatsMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

            binding.tvClub1Shots.text = matchData.shots1.toString()
            binding.tvClub2Shots.text = matchData.shots2.toString()
            binding.tvClub1ShotsOnTarget.text = matchData.shotsOnTarget1.toString()
            binding.tvClub2ShotsOnTarget.text = matchData.shotsOnTarget2.toString()
            binding.tvClub1Possession.text = (matchData.possession1 * 100).toInt().toString() + "%"
            binding.tvClub2Possession.text = (matchData.possession2 * 100).toInt().toString() + "%"
            binding.tvClub1Passes.text = matchData.passes1.toString()
            binding.tvClub2Passes.text = matchData.passes2.toString()
            binding.tvClub1PassAccuracy.text = (matchData.passAccuracy1 * 100).toInt().toString() + "%"
            binding.tvClub2PassAccuracy.text = (matchData.passAccuracy2 * 100).toInt().toString() + "%"
            binding.tvClub1Fouls.text = matchData.fouls1.toString()
            binding.tvClub2Fouls.text = matchData.fouls2.toString()
            binding.tvClub1YellowCards.text = matchData.yellowCards1.toString()
            binding.tvClub2YellowCards.text = matchData.yellowCards2.toString()
            binding.tvClub1RedCards.text = matchData.redCards1.toString()
            binding.tvClub2RedCards.text = matchData.redCards2.toString()
            binding.tvClub1Offsides.text = matchData.offsides1.toString()
            binding.tvClub2Offsides.text = matchData.offsides2.toString()
            binding.tvClub1Corners.text = matchData.corners1.toString()
            binding.tvClub2Corners.text = matchData.corners2.toString()


        }
    }

    inner class NewsViewHolder(private val binding: ItemVpNewsMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

            binding.rvNews.adapter = newsAdapter
        }
    }

    inner class CommentViewHolder(private val binding: ItemVpCommentMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.rvComments.adapter = commentAdapter

            binding.ivSendButton.setOnClickListener {
                addComment(binding.etComment.text.toString())
                binding.etComment.text.clear()
            }
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
            is NewsViewHolder -> holder.bind()
            is CommentViewHolder -> holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return MatchDetailViewType.entries[position].type
    }

    fun setComments(comments: List<CommentEntity>) {
        commentAdapter.setComments(comments)
        Log.d("MatchDetailAdapter", "setComments: $comments")
        notifyDataSetChanged()
    }

    fun setUser(users: List<UserModel>) {
        commentAdapter.setUsers(users)
        notifyDataSetChanged()
    }

    fun setMatchData(matchData: MatchDataEntity) {
        this.matchData = matchData
        notifyDataSetChanged()
    }

    fun setTimeline(timeline: List<Timeline>) {
        timelineAdapter.setTimeline(timeline)
        notifyDataSetChanged()
    }

    fun setNews(news: List<NewsEntity>) {
        newsAdapter.setList(news)
        notifyDataSetChanged()
    }
}