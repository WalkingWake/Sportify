package dev.ptit.ui.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.ptit.data.league.LeagueEntity
import dev.ptit.databinding.ItemRvLeagueBinding

class LeagueAdapter(
    private val onLeagueClick: (LeagueEntity) -> Unit
) : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    private var leagueList = listOf<LeagueEntity>()

    inner class ViewHolder(
        private val binding: ItemRvLeagueBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(leagueEntity: LeagueEntity) {
            binding.clLeagueItem.setOnClickListener {
                onLeagueClick(leagueEntity)
            }
            Glide.with(itemView.context).load(leagueEntity.logo).into(binding.ivLeague)
            binding.tvLeague.text = leagueEntity.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRvLeagueBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int {
        return leagueList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(leagueList[position])
    }

    fun setList(list: List<LeagueEntity>) {
        this.leagueList = list
        notifyDataSetChanged()
    }
}