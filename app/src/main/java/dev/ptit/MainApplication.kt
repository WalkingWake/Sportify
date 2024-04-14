package dev.ptit

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import dev.ptit.data.FirebaseService
import dev.ptit.data.news.NewsRepository
import javax.inject.Inject


@HiltAndroidApp
class MainApplication : Application() {

    @Inject
    lateinit var firebaseService: FirebaseService

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
        firebaseService.init()
    }
}