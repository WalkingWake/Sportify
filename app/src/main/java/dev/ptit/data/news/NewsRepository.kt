package dev.ptit.data.news

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

object NewsRepository {

    var news: MutableList<NewsModel> = mutableListOf()

    init {
        val firebaseInstance = FirebaseDatabase.getInstance()
        val firebaseDatabase = firebaseInstance.getReference("news")
        firebaseDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val newsList = mutableListOf<NewsModel>()
                for (childSnapshot in snapshot.children) {
                    val news = childSnapshot.getValue(NewsModel::class.java)
                    news?.let {
                        newsList.add(it)
                    }
                }
                Log.d("NewsRepository", "onDataChange: $newsList")
                news = newsList
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }


    fun getAllNewsTag(): List<String> {
        return listOf("All", "Trending", "Premier League", "La Liga", "Messi", "Ronaldo")
    }
}