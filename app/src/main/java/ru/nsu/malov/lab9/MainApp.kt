package ru.nsu.malov.lab9

import android.app.Application
import android.content.Context
import android.util.Log
import ru.nsu.malov.lab9.di.AppComponent
import ru.nsu.malov.lab9.di.DaggerAppComponent
import ru.nsu.malov.lab9.log_tags.LogTags

class MainApp : Application() {
    lateinit var appComponent: AppComponent
    private lateinit var context: Context
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        appComponent = DaggerAppComponent.create()
        Log.d(LogTags.login, "${appComponent != null} ")
    }
}