package dev.ptit.ui.adapter.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.ptit.data.news.NewsEntity
import dev.ptit.databinding.ItemRvNewsBinding

class NewsRVAdapter(
    private val onNewsClick: (NewsEntity) -> Unit
) : RecyclerView.Adapter<NewsRVAdapter.ViewHolder>() {

    private var newsList = listOf<NewsEntity>()

    inner class ViewHolder(private val binding: ItemRvNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newsEntity: NewsEntity) {
            binding.clNewsItem.setOnClickListener {
                onNewsClick(newsEntity)
            }
            Glide.with(itemView.context).load(newsEntity.image).into(binding.ivNewsImage)
            binding.tvNewsTitle.text = newsEntity.title
            binding.tvNewsDescription.text = newsEntity.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRvNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    fun setList(list: List<NewsEntity>) {
        this.newsList = list
        notifyDataSetChanged()
    }
}