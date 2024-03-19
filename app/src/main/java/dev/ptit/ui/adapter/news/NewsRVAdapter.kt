package dev.ptit.ui.adapter.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.ptit.data.news.NewsModel
import dev.ptit.databinding.ItemRvNewsBinding

class NewsRVAdapter(
    private val onNewsClick: (NewsModel) -> Unit
) : RecyclerView.Adapter<NewsRVAdapter.ViewHolder>() {

    private var newsList = listOf<NewsModel>()

    inner class ViewHolder(private val binding: ItemRvNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newsModel: NewsModel) {
            binding.clNewsItem.setOnClickListener {
                onNewsClick(newsModel)
            }
//            Glide.with(itemView.context).load(newsModel.image).into(binding.ivNewsImage)
//            binding.tvNewsTitle.text = newsModel.title
//            binding.tvNewsDescription.text = newsModel.description
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

    fun setList(list: List<NewsModel>) {
        this.newsList = list
        notifyDataSetChanged()
    }
}