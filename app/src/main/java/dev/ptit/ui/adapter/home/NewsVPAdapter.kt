package dev.ptit.ui.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.ptit.data.news.NewsModel
import dev.ptit.databinding.ItemVpNewsBinding

class NewsVPAdapter : RecyclerView.Adapter<NewsVPAdapter.ViewHolder>() {

    private var newsList = listOf<NewsModel>()

    inner class ViewHolder (private val binding : ItemVpNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsModel : NewsModel) {
//            Glide.with(itemView.context).load(newsModel.image).into(binding.ivNews)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemVpNewsBinding.inflate(
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