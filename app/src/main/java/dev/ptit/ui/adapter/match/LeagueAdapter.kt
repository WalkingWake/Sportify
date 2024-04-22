package dev.ptit.ui.adapter.match

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.ptit.R
import dev.ptit.data.league.LeagueEntity
import dev.ptit.databinding.ItemRvLeagueBinding

class LeagueAdapter(
    private val onLeagueClick: (Int) -> Unit
) : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    private var leagueList = listOf<LeagueEntity>()
    private var selectedLeague : Int? = 0

    inner class ViewHolder(
        private val binding: ItemRvLeagueBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(leagueEntity: LeagueEntity) {

            if (selectedLeague == leagueEntity.remoteId) {
                binding.clLeague.setBackgroundColor(itemView.context.getColor(R.color.primary))
            } else {
                binding.clLeague.setBackgroundColor(itemView.context.getColor(R.color.transparent))
            }
            binding.clLeague.setOnClickListener {
                onLeagueClick(leagueEntity.remoteId)
            }
            binding.tvLeague.text = leagueEntity.name
            Glide.with(itemView.context).load(leagueEntity.logo).into(binding.ivLeague)
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

    fun setSelectedLeague(leagueId: Int?) {
        this.selectedLeague = leagueId
        notifyDataSetChanged()
    }
}