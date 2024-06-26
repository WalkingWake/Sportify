package dev.ptit.setup.extension

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun <T> DatabaseReference.addValueEventListenerFlow(dataType: Class<T>): Flow<List<T>> =
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
