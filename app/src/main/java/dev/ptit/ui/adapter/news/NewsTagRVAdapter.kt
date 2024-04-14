package dev.ptit.ui.adapter.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dev.ptit.R
import dev.ptit.data.tag.TagEntity
import dev.ptit.databinding.ItemRvNewsTagBinding

class NewsTagRVAdapter(
    private val onTagClick: (TagEntity) -> Unit
) : RecyclerView.Adapter<NewsTagRVAdapter.ViewHolder>() {

    private var tagList = listOf<TagEntity>()
    private var selectedTag = TagEntity()

    inner class ViewHolder(private val binding: ItemRvNewsTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tag: TagEntity) {
            binding.tvTag.text = tag.tag
            binding.clTag.setOnClickListener {
                onTagClick(tag)
            }
            if (tag == selectedTag) {
                binding.clTag.background =
                    ContextCompat.getDrawable(itemView.context, R.drawable.background_selected_tag)

                binding.tvTag.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.white
                    )
                )
            } else {
                binding.clTag.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.background_unselected_tag
                )

                binding.tvTag.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.content
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRvNewsTagBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return tagList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tagList[position])
    }

    fun setList(list: List<TagEntity>) {
        this.tagList = list
        notifyDataSetChanged()
    }

    fun setSelectedTag(tag: TagEntity) {
        this.selectedTag = tag
        notifyDataSetChanged()
    }
}