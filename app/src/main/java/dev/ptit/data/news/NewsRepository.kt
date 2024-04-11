package dev.ptit.data.news

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

object NewsRepository {

    var news: MutableList<NewsModel> = mutableListOf()
    private val firebaseInstance = FirebaseDatabase.getInstance()

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


    fun getAllNews(): Flow<List<NewsModel>> {
        return firebaseInstance.getReference("news")
            .addValueEventListenerFlow(NewsModel::class.java)
//        val newsRef = firebaseInstance.getReference("news")
//        newsRef.addValueEventListenerFlow(NewsModel::class.java).collect {
//            if (it != null) {
//                news.add(it)
//                emit(news)
//            }
//        }
    }


    fun getAllNewsTag(): List<String> {
        return listOf("All", "Trending", "Premier League", "La Liga", "Messi", "Ronaldo")
    }
}