package dev.ptit.ui.adapter.match

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.ptit.R
import dev.ptit.data.league.LeagueModel
import dev.ptit.databinding.ItemRvLeagueBinding

class LeagueAdapter(
    private val onLeagueClick: (Int) -> Unit
) : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    private var leagueList = listOf<LeagueModel>()
    private var selectedLeague = 0

    inner class ViewHolder(
        private val binding: ItemRvLeagueBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(leagueModel: LeagueModel) {

            if (selectedLeague == leagueModel.id) {
                binding.clLeague.setBackgroundColor(itemView.context.getColor(R.color.primary))
            } else {
                binding.clLeague.setBackgroundColor(itemView.context.getColor(R.color.transparent))
            }
            binding.clLeague.setOnClickListener {
                onLeagueClick(leagueModel.id)
                setSelectedLeague(leagueModel.id)
            }
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

    fun setSelectedLeague(leagueId: Int) {
        this.selectedLeague = leagueId
        notifyDataSetChanged()
    }
}