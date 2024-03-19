package dev.ptit.ui.screen.news

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ptit.data.news.NewsModel
import dev.ptit.data.news.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _selectedTag = MutableStateFlow("All")
    val selectedTag = _selectedTag.asStateFlow()

    fun setSelectedTag(tag: String) {
        _selectedTag.value = tag
    }

    fun getAllNews() : List<NewsModel> {
        return newsRepository.getAllNews()
    }

    fun getAllTags() : List<String> {
        return newsRepository.getAllNewsTag()
    }

}