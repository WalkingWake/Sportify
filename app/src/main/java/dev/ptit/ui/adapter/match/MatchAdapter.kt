package dev.ptit.ui.adapter.match

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.ptit.R
import dev.ptit.data.league.LeagueEntity
import dev.ptit.data.match.MatchEntity
import dev.ptit.data.team.TeamEntity
import dev.ptit.databinding.ItemRvMatchHeaderBinding
import dev.ptit.databinding.ItemRvPastMatchBinding
import dev.ptit.databinding.ItemRvUpcomingMatchBinding
import dev.ptit.setup.extension.formattedDateToLong
import dev.ptit.setup.extension.longToFormattedDate
import dev.ptit.setup.utils.Utils

class MatchAdapter(
    private val onLeagueClick: (Int) -> Unit,
    private val onMatchClick: (MatchEntity) -> Unit,
    private val onUpcomingClick: (Boolean) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var leagueList = listOf<LeagueEntity>()
    private var isUpcomingState = true

    private var matchList = listOf<MatchEntity>()
    private var teamList = listOf<TeamEntity>()

    private var isSearching = false

    private val leagueAdapter: LeagueAdapter = LeagueAdapter {
        onLeagueClick(it)
    }

    inner class MatchHeaderViewHolder(private val binding: ItemRvMatchHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

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
        fun bind(matchEntity: MatchEntity, position: Int) {
            val team1 = getTeamById(matchEntity.team1Id)
            val team2 = getTeamById(matchEntity.team2Id)
            val league = getLeagueById(matchEntity.leagueId)

            if (position == 0 || !Utils.checkSameDay(
                    matchList[position - 1].startTime,
                    matchEntity.startTime
                )
            ) {
                binding.tvMatchDate.visibility = View.VISIBLE
                binding.tvMatchDate.text =
                    matchEntity.startTime.formattedDateToLong().longToFormattedDate("EE dd/MM")
            } else {
                binding.tvMatchDate.visibility = View.GONE
            }

            binding.tvMatchTime.text =
                matchEntity.startTime.formattedDateToLong().longToFormattedDate("HH:mm")

            Glide.with(itemView.context)
                .load(league?.logo)
                .into(binding.ivLeague)

            binding.tvClub1Name.text = team1?.name
            binding.tvClub2Name.text = team2?.name

            Glide.with(itemView.context)
                .load(team1?.logo)
                .into(binding.ivClub1Image)

            Glide.with(itemView.context)
                .load(team2?.logo)
                .into(binding.ivClub2Image)

            binding.clItemMatch.setOnClickListener {
                onMatchClick(matchList[adapterPosition - 1])
            }
        }
    }

    inner class PastMatchViewHolder(private val binding: ItemRvPastMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(matchEntity: MatchEntity, position: Int) {

            if (position == 0 || !Utils.checkSameDay(
                    matchList[position - 1].startTime,
                    matchEntity.startTime
                )
            ) {
                binding.tvMatchDate.visibility = View.VISIBLE
                binding.tvMatchDate.text =
                    matchEntity.startTime.formattedDateToLong().longToFormattedDate("EE dd/MM")
            } else {
                binding.tvMatchDate.visibility = View.GONE
            }

            val team1 = getTeamById(matchEntity.team1Id)
            val team2 = getTeamById(matchEntity.team2Id)
            val league = getLeagueById(matchEntity.leagueId)

            Glide.with(itemView.context)
                .load(league?.logo)
                .into(binding.ivLeague)

            binding.tvClub1Name.text = team1?.name
            binding.tvClub2Name.text = team2?.name

            Glide.with(itemView.context)
                .load(team1?.logo)
                .into(binding.ivClub1Image)

            Glide.with(itemView.context)
                .load(team2?.logo)
                .into(binding.ivClub2Image)

            binding.tvClub1Score.text = matchEntity.team1Score.toString()
            binding.tvClub2Score.text = matchEntity.team2Score.toString()

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
        return if (!isSearching) 1 + matchList.size else matchList.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MatchHeaderViewHolder -> holder.bind()
            is UpcomingMatchViewHolder -> holder.bind(
                matchList[if (!isSearching) position - 1 else position],
                if (!isSearching) position - 1 else position
            )

            is PastMatchViewHolder -> holder.bind(
                matchList[if (!isSearching) position - 1 else position],
                if (!isSearching) position - 1 else position
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(position == 0 && !isSearching) return MatchViewType.HEADER.type
        return if (matchList[if(!isSearching) position - 1 else position].startTime.formattedDateToLong() > System.currentTimeMillis()) {
            MatchViewType.UPCOMING_MATCH.type
        } else {
            MatchViewType.PAST_MATCH.type
        }
//        return when (position) {
//            0 -> MatchViewType.HEADER.type
//            else -> {
//                MatchViewType.UPCOMING_MATCH.type
//                if (matchList[position - 1].startTime.formattedDateToLong() > System.currentTimeMillis()) {
//                    MatchViewType.UPCOMING_MATCH.type
//                } else {
//                    MatchViewType.PAST_MATCH.type
//                }
//            }
//        }
    }

    fun setLeagueList(leagueList: List<LeagueEntity>) {
        this.leagueList = leagueList
        notifyDataSetChanged()
    }

    fun setMatchList(matchList: List<MatchEntity>) {
        this.matchList = matchList
        notifyDataSetChanged()
    }

    fun setTeamList(teamList: List<TeamEntity>) {
        this.teamList = teamList
        notifyDataSetChanged()
    }

    fun setUpcomingState(isUpcomingState: Boolean) {
        this.isUpcomingState = isUpcomingState
        notifyDataSetChanged()
    }

    private fun getTeamById(id: Int): TeamEntity? {
        return teamList.find { it.remoteId == id }
    }

    private fun getLeagueById(id: Int): LeagueEntity? {
        return leagueList.find { it.remoteId == id }
    }

    fun setSelectedLeague(leagueId: Int) {
        leagueAdapter.setSelectedLeague(leagueId)
    }

    fun setIsSearching(isSearching: Boolean) {
        this.isSearching = isSearching
        notifyDataSetChanged()
    }
}