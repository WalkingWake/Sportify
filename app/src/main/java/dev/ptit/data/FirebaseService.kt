package dev.ptit.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dev.ptit.data.news.NewsEntity
import dev.ptit.data.news.NewsRepository
import dev.ptit.data.newstagmapping.NewsTagEntity
import dev.ptit.data.newstagmapping.NewsTagRepository
import dev.ptit.data.tag.TagEntity
import dev.ptit.data.tag.TagRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class FirebaseService(
    private val firebaseInstance: FirebaseDatabase,
    private val newsRepository: NewsRepository,
    private val tagRepository: TagRepository,
    private val newsTagRepository: NewsTagRepository
) {

    fun init() {
        getAllNews()
        getAllTags()
        getAllNewsTag()
    }

    private fun <T> DatabaseReference.addValueEventListenerFlow(dataType: Class<T>): Flow<List<T>> =
        callbackFlow {
            val listener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val list = dataSnapshot.children.mapNotNull { it.getValue(dataType) }
                    trySend(list)
                }

                override fun onCancelled(error: DatabaseError) {
                    cancel()
                }
            }
            addValueEventListener(listener)
            awaitClose { removeEventListener(listener) }
        }


    private fun getAllNews() {
        CoroutineScope(Dispatchers.IO).launch {
            firebaseInstance.getReference("news")
                .addValueEventListenerFlow(NewsEntity::class.java).collect { news ->
                    newsRepository.insertNews(news)
                }
        }
    }

    private fun getAllTags() {
        CoroutineScope(Dispatchers.IO).launch {
            firebaseInstance.getReference("tags")
                .addValueEventListenerFlow(TagEntity::class.java).collect { tags ->
                    tagRepository.insertTags(tags)
                }
        }
    }

    private fun getAllNewsTag() {
        CoroutineScope(Dispatchers.IO).launch {
            firebaseInstance.getReference("news_tag")
                .addValueEventListenerFlow(NewsTagEntity::class.java).collect { newsTags ->
                    newsTagRepository.insertNewsTags(newsTags)
                }
        }
    }

}