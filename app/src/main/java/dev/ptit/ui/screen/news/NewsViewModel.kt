package dev.ptit.ui.screen.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ptit.data.news.NewsEntity
import dev.ptit.data.news.NewsRepository
import dev.ptit.data.newstagmapping.NewsTagEntity
import dev.ptit.data.newstagmapping.NewsTagRepository
import dev.ptit.data.tag.TagEntity
import dev.ptit.data.tag.TagRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val tagRepository: TagRepository,
    private val newsTagRepository: NewsTagRepository
) : ViewModel() {

    private val allNews = MutableStateFlow<List<NewsEntity>>(listOf())

    private val _news = MutableStateFlow<List<NewsEntity>>(listOf())
    val news = _news.asStateFlow()

    private val _tags = MutableStateFlow<List<TagEntity>>(listOf())
    val tags = _tags.asStateFlow()

    private val newsTags = MutableStateFlow<List<NewsTagEntity>>(listOf())

    private val _selectedTag = MutableStateFlow(tagRepository.getTagAll())
    val selectedTag = _selectedTag.asStateFlow()

    private val _selectedNews = MutableStateFlow(NewsEntity())
    val selectedNews = _selectedNews.asStateFlow()

    init {
        viewModelScope.launch {
            newsRepository.getAllNews().collect { newsList ->
                allNews.update { newsList }
                updateUIState()
            }
        }

        viewModelScope.launch {
            tagRepository.getAllTags().collect { tagList ->
                _tags.update {
                    listOf(tagRepository.getTagAll()) + tagList
                }
            }
        }

        viewModelScope.launch {
            newsTagRepository.getAllNewsTags().collect { newsTagList ->
                newsTags.update { newsTagList }
            }
        }

        viewModelScope.launch {
            selectedTag.collect {
                updateUIState()
            }
        }
    }

    private fun updateUIState(){
        if (selectedTag.value == tagRepository.getTagAll()) {
            _news.update {
                allNews.value
            }
        } else {
            _news.update {
                getNewsByTag(_tags.value.first {
                    it == selectedTag.value
                })
            }
        }
    }

    fun setSelectedTag(tag: TagEntity) {
        _selectedTag.value = tag
    }

    fun setSelectedNews(news: NewsEntity) {
        _selectedNews.value = news
    }

    private fun getNewsByTag(tag: TagEntity): List<NewsEntity> {
        val newsList = mutableListOf<NewsEntity>()

        newsTags.value.forEach { newsTag ->
            if (newsTag.tagId == tag.remoteId) {
                val news: NewsEntity? = allNews.value.firstOrNull {
                    it.remoteId == newsTag.newsId
                }
                news?.let {
                    newsList.add(it)
                }
            }
        }

        return newsList
    }

}