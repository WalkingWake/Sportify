package dev.ptit.ui.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.ptit.data.league.LeagueModel
import dev.ptit.databinding.ItemRvLeagueBinding

class LeagueAdapter : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    private var leagueList = listOf<LeagueModel>()

    inner class ViewHolder(
        private val binding: ItemRvLeagueBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(leagueModel: LeagueModel) {
//            Glide.with(itemView.context).load(leagueModel.logo).into(binding.ivLeague)
            binding.tvLeague.text = leagueModel.name
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

    fun setList(list: List<LeagueModel>) {
        this.leagueList = list
        notifyDataSetChanged()
    }
}