package dev.ptit.ui.adapter.club

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.ptit.databinding.ItemRvClubBinding

class ClubAdapter(
    private val onClubClick: () -> Unit
) : RecyclerView.Adapter<ClubAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ItemRvClubBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.clClubItem.setOnClickListener {
                onClubClick()
            }
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
        return 16
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }
}