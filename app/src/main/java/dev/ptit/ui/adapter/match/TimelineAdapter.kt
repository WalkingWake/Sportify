package dev.ptit.ui.adapter.match

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.ptit.databinding.ItemTimelineBinding
import dev.ptit.setup.extension.Timeline

class TimelineAdapter : RecyclerView.Adapter<TimelineAdapter.TimelineViewHolder>() {

    private var timeline = listOf<Timeline>()

    inner class TimelineViewHolder(private val binding: ItemTimelineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(timeline: Timeline) {
            if (timeline.side == 1) {
                binding.clTimelineTeam1.visibility = View.VISIBLE
                binding.clTimelineTeam2.visibility = View.GONE
                Glide.with(itemView.context)
                    .load(timeline.icon)
                    .into(binding.ivIcon1)
                binding.tvTime1.text = timeline.time.toString()
                binding.tvTitle1.text = timeline.title
            } else {
                binding.clTimelineTeam2.visibility = View.VISIBLE
                binding.clTimelineTeam1.visibility = View.GONE
                Glide.with(itemView.context)
                    .load(timeline.icon)
                    .into(binding.ivIcon2)
                binding.tvTime2.text = timeline.time.toString()
                binding.tvTitle2.text = timeline.title
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        return TimelineViewHolder(
            ItemTimelineBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return timeline.size
    }

    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        holder.bind(timeline[position])
    }

    fun setTimeline(timeline: List<Timeline>) {
        this.timeline = timeline
        notifyDataSetChanged()
    }
}