package dev.ptit.ui.adapter.club

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.ptit.data.team.TeamEntity
import dev.ptit.databinding.ItemRvClubBinding

class ClubAdapter(
    private val onClubClick: () -> Unit
) : RecyclerView.Adapter<ClubAdapter.ViewHolder>() {

    private var teams = listOf<TeamEntity>()

    inner class ViewHolder(
        private val binding: ItemRvClubBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(team : TeamEntity) {
            binding.clClubItem.setOnClickListener {
                onClubClick()
            }
            binding.tvClubName.text = team.name
            Glide.with(binding.root).load(team.logo).into(binding.ivClubLogo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRvClubBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(teams[position])
    }

    fun setList(teams: List<TeamEntity>) {
        this.teams = teams
        notifyDataSetChanged()
    }
}