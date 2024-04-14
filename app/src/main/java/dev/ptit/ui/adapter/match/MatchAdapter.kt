package dev.ptit.ui.adapter.match

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.ptit.R
import dev.ptit.data.league.LeagueEntity
import dev.ptit.data.match.MatchModel
import dev.ptit.databinding.ItemRvMatchHeaderBinding
import dev.ptit.databinding.ItemRvPastMatchBinding
import dev.ptit.databinding.ItemRvUpcomingMatchBinding

class MatchAdapter(
    private val onLeagueClick: (LeagueEntity) -> Unit,
    private val onMatchClick: (MatchModel) -> Unit,
    private val onUpcomingClick: (Boolean) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var leagueList = listOf<LeagueEntity>()
    private var isUpcomingState = true

    private var matchList = listOf<MatchModel>()


    inner class MatchHeaderViewHolder(private val binding: ItemRvMatchHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val leagueAdapter: LeagueAdapter = LeagueAdapter {
            onLeagueClick(it)
        }

        fun bind() {
            binding.tvUpcoming.setOnClickListener {
                onUpcomingClick(true)
            }

            binding.tvPastMatches.setOnClickListener {
                onUpcomingClick(false)
            }

            if (binding.rvLeagues.adapter == null) {
                binding.rvLeagues.adapter = leagueAdapter
            }
            leagueAdapter.setList(leagueList)

            if (isUpcomingState) {
                binding.tvUpcoming.setTextColor(itemView.context.getColor(R.color.content))
                binding.vUpcomingHighlight.visibility = View.VISIBLE
                binding.vPastMatchesHighlight.visibility = View.INVISIBLE
                binding.tvPastMatches.setTextColor(itemView.context.getColor(R.color.secondary_content))
            } else {
                binding.tvPastMatches.setTextColor(itemView.context.getColor(R.color.content))
                binding.vPastMatchesHighlight.visibility = View.VISIBLE
                binding.vUpcomingHighlight.visibility = View.INVISIBLE
                binding.tvUpcoming.setTextColor(itemView.context.getColor(R.color.secondary_content))
            }
        }
    }

    inner class UpcomingMatchViewHolder(private val binding: ItemRvUpcomingMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            if(position% 4 == 0){
                binding.tvMatchDate.visibility = View.VISIBLE
            }
            binding.clItemMatch.setOnClickListener {
                onMatchClick(matchList[adapterPosition - 1])
            }
        }
    }

    inner class PastMatchViewHolder(private val binding: ItemRvPastMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            if(position% 4 == 0){
                binding.tvMatchDate.visibility = View.VISIBLE
            }
            binding.clItemMatch.setOnClickListener {
                onMatchClick(matchList[adapterPosition - 1])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MatchViewType.HEADER.type -> MatchHeaderViewHolder(
                ItemRvMatchHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            MatchViewType.UPCOMING_MATCH.type -> {
                UpcomingMatchViewHolder(
                    ItemRvUpcomingMatchBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            MatchViewType.PAST_MATCH.type -> {
                PastMatchViewHolder(
                    ItemRvPastMatchBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return 1 + matchList.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MatchHeaderViewHolder -> holder.bind()
            is UpcomingMatchViewHolder -> holder.bind(position - 1)
            is PastMatchViewHolder -> holder.bind(position - 1)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> MatchViewType.HEADER.type
            else -> {
                if (matchList[position - 1].isUpcoming) {
                    MatchViewType.UPCOMING_MATCH.type
                } else {
                    MatchViewType.PAST_MATCH.type
                }
            }
        }
    }

    fun setLeagueList(leagueList: List<LeagueEntity>) {
        this.leagueList = leagueList
        notifyDataSetChanged()
    }

    fun setMatchList(matchList: List<MatchModel>) {
        this.matchList = matchList
        notifyDataSetChanged()
    }

    fun setUpcomingState(isUpcomingState: Boolean) {
        this.isUpcomingState = isUpcomingState
        notifyDataSetChanged()
    }
}