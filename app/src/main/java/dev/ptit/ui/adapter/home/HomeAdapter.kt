package dev.ptit.ui.adapter.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import dev.ptit.data.league.LeagueEntity
import dev.ptit.data.match.MatchEntity
import dev.ptit.data.news.NewsEntity
import dev.ptit.data.team.TeamEntity
import dev.ptit.databinding.ItemRvHomeHeaderBinding
import dev.ptit.databinding.ItemRvUpcomingMatchBinding
import dev.ptit.setup.extension.formattedDateToLong
import dev.ptit.setup.extension.longToFormattedDate
import dev.ptit.setup.utils.Utils

class HomeAdapter(
    private val onViewAllNewsClick : () -> Unit,
    private val onViewAllLeaguesClick : () -> Unit,
    private val onViewAllMatchesClick : () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var leagueList = listOf<LeagueEntity>()
    private var newsList = listOf<NewsEntity>()
    private var matchList = listOf<MatchEntity>()
    private var teamsList = listOf<TeamEntity>()

    inner class HomeHeaderViewHolder(private val binding: ItemRvHomeHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val leagueAdapter: LeagueAdapter = LeagueAdapter()
        private val newsVPAdapter: NewsVPAdapter = NewsVPAdapter()

        fun bind() {

            binding.tvViewAllNews.setOnClickListener {
                onViewAllNewsClick()
            }

            binding.tvViewAllLeagues.setOnClickListener {
                onViewAllLeaguesClick()
            }

            binding.tvViewAllMatches.setOnClickListener {
                onViewAllMatchesClick()
            }

            binding.rvAllLeagues.adapter = leagueAdapter.apply {
                setList(leagueList)
            }

            binding.vpNews.adapter = newsVPAdapter.apply {
                setList(newsList)
            }

            if (newsList.isNotEmpty()) {
                binding.indicatorNews.setIndicatorNumber(newsList.size)
            }

            binding.vpNews.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.indicatorNews.setActiveIndicator(position)
                }
            })

        }
    }

    inner class MatchViewHolder(private val binding: ItemRvUpcomingMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(matchEntity: MatchEntity, position: Int) {
            val team1 = getTeamById(matchEntity.team1Id)
            val team2 = getTeamById(matchEntity.team2Id)
            val league = getLeagueById(matchEntity.leagueId)

            if(position == 0 || !Utils.checkSameDay(matchList[position - 1].startTime, matchEntity.startTime)) {
                binding.tvMatchDate.visibility = View.VISIBLE
                binding.tvMatchDate.text = matchEntity.startTime.formattedDateToLong().longToFormattedDate("EE dd/MM")
            } else {
                binding.tvMatchDate.visibility = View.GONE
            }

            binding.tvMatchTime.text = matchEntity.startTime.formattedDateToLong().longToFormattedDate("HH:mm")

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
//                onMatchClick(matchList[adapterPosition - 1])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HomeViewType.HEADER.type -> HomeHeaderViewHolder(
                ItemRvHomeHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> MatchViewHolder(
                ItemRvUpcomingMatchBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return 1 + matchList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeHeaderViewHolder -> holder.bind()
            is MatchViewHolder -> holder.bind(matchList[position - 1], position - 1)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            HomeViewType.HEADER.type
        } else {
            HomeViewType.MATCH.type
        }
    }

    fun setLeagueList(list: List<LeagueEntity>) {
        this.leagueList = list
        notifyDataSetChanged()
    }

    fun setNewsList(list: List<NewsEntity>) {
        this.newsList = list
        notifyDataSetChanged()
    }

    fun setMatchList(list: List<MatchEntity>) {
        this.matchList = list
        notifyDataSetChanged()
    }

    fun setTeamList(list: List<TeamEntity>) {
        this.teamsList = list
        notifyDataSetChanged()
    }

    private fun getTeamById(id: Int): TeamEntity? {
        return teamsList.find { it.remoteId == id }
    }


    private fun getLeagueById(id: Int): LeagueEntity? {
        return leagueList.find { it.remoteId == id }
    }
}