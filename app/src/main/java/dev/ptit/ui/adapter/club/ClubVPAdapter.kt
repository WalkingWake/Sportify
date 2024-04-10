package dev.ptit.ui.adapter.club

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.ptit.databinding.ItemInformationClubBinding
import dev.ptit.databinding.ItemMatchClubBinding
import dev.ptit.databinding.ItemPlayerClubBinding
import dev.ptit.databinding.ItemTableClubBinding

class ClubVPAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class MatchViewHolder(
        private val binding: ItemMatchClubBinding
    ) : RecyclerView.ViewHolder(binding.root)

    inner class TableViewHolder(
        private val binding: ItemTableClubBinding
    ) : RecyclerView.ViewHolder(binding.root)

    inner class PlayerViewHolder(
        private val binding: ItemPlayerClubBinding
    ) : RecyclerView.ViewHolder(binding.root)

    inner class InformationViewHolder(
        private val binding: ItemInformationClubBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ClubViewType.MATCH.viewType -> MatchViewHolder(
                ItemMatchClubBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            ClubViewType.PLAYER.viewType -> PlayerViewHolder(
                ItemPlayerClubBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            ClubViewType.TABLE.viewType -> TableViewHolder(
                ItemTableClubBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            ClubViewType.INFORMATION.viewType -> InformationViewHolder(
                ItemInformationClubBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return ClubViewType.entries.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        when(holder){
//            is MatchViewHolder
//        }
    }

    override fun getItemViewType(position: Int): Int {
        return ClubViewType.entries[position].viewType
    }
}
