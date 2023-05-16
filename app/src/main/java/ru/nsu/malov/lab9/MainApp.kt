package ru.nsu.malov.lab9

import android.app.Application
import ru.nsu.malov.lab9.di.AppComponent
import ru.nsu.malov.lab9.di.DaggerAppComponent

class MainApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}