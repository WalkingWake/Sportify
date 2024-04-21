package dev.ptit.data.user

import com.google.firebase.database.FirebaseDatabase
import dev.ptit.setup.extension.addValueEventListenerFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(
    private val firebaseInstance: FirebaseDatabase
) {

    private val users = mutableListOf<UserModel>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            firebaseInstance.getReference("users")
                .addValueEventListenerFlow(UserModel::class.java).collect { news ->
                    users.clear()
                    users.addAll(news)
                }
        }
    }

    fun validateUser(email: String, password: String): Int? {
        return users.find { it.email == email && it.password == password }?.remoteId
    }

    fun registerUser(user: UserModel) {
        val id = getNextId()
        val userReference = firebaseInstance.getReference("users/$id")
        userReference.setValue(user.apply {
            remoteId = id
        })
    }

    fun getUserIdByEmail(email: String): Int? {
        return users.find { it.email == email }?.remoteId
    }

    fun getUserByEmail(email: String): UserModel? {
        return users.find { it.email == email }
    }

    fun updatePassword(userId : Int, password: String) {
        val userReference = firebaseInstance.getReference("users/$userId")
        userReference.child("password").setValue(password)
    }

    fun checkUserExist(email: String): Boolean {
        return users.any { it.email.lowercase() == email.lowercase() }
    }

    private fun getNextId(): Int {
        return users.maxOfOrNull { it.remoteId }?.plus(1) ?: 1
    }
}