package ru.nsu.malov.lab9.view_models

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nsu.malov.lab9.MainApp
//import ru.nsu.malov.lab9.di.AppComponent
import ru.nsu.malov.lab9.network.StatusValue
import ru.nsu.malov.lab9.repository.LoginRepository
import javax.inject.Inject

class LoginViewModel : ViewModel(){
    private val _status = MutableLiveData<StatusValue>()
    private val status: LiveData<StatusValue> = _status
    private lateinit var loginRepository: LoginRepository

    fun authUser(){
        val appComponent = MainApp().appComponent
        loginRepository = appComponent.getLoginRepository()
    }
}