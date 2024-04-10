package dev.ptit

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dev.ptit.data.news.NewsRepository


@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NewsRepository
    }
}