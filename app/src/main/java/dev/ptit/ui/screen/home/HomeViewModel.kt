package dev.ptit.ui.screen.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ptit.data.news.NewsModel
import dev.ptit.data.news.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    fun getAllNews() : Flow<List<NewsModel>> {
        return newsRepository.getAllNews()
    }
}