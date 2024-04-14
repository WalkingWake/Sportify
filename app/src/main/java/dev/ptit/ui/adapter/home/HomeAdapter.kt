package dev.ptit.ui.adapter.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import dev.ptit.data.league.LeagueEntity
import dev.ptit.data.match.MatchModel
import dev.ptit.data.news.NewsEntity
import dev.ptit.databinding.ItemRvHomeHeaderBinding
import dev.ptit.databinding.ItemRvUpcomingMatchBinding

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var leagueList = listOf<LeagueEntity>()
    private var newsList = listOf<NewsEntity>()
    private var matchList = listOf<MatchModel>()

    inner class HomeHeaderViewHolder(private val binding: ItemRvHomeHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val leagueAdapter: LeagueAdapter = LeagueAdapter()
        private val newsVPAdapter: NewsVPAdapter = NewsVPAdapter()

        fun bind() {

            binding.rvAllLeagues.adapter = leagueAdapter.apply {
                setList(leagueList)
            }

            binding.vpNews.adapter = newsVPAdapter.apply {
                setList(newsList)
            }

            Log.d("HomeAdapter", "newsList: $newsList")

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
        fun bind(matchModel: MatchModel) {
            binding.tvMatchTime.visibility = View.GONE
            binding.tvClub1Name.text = matchModel.homeTeam
            binding.tvClub2Name.text = matchModel.awayTeam
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
            is MatchViewHolder -> holder.bind(matchList[position - 1])
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

    fun setMatchList(list: List<MatchModel>) {
        this.matchList = list
        notifyDataSetChanged()
    }
}