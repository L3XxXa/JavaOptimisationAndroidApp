package ru.nsu.malov.lab9.view_models

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nsu.malov.lab9.MainApp
import ru.nsu.malov.lab9.log_tags.LogTags
//import ru.nsu.malov.lab9.di.AppComponent
import ru.nsu.malov.lab9.network.StatusValue
import ru.nsu.malov.lab9.network.serializers.LoginRequestSerializer
import ru.nsu.malov.lab9.network.serializers.LoginResponseSerializer
import ru.nsu.malov.lab9.repository.LoginRepository
import javax.inject.Inject

class LoginViewModel : ViewModel(){
    private val _status = MutableLiveData<StatusValue>()
    val status: LiveData<StatusValue> = _status
    private val _response = MutableLiveData<LoginResponseSerializer>()
    val response: LiveData<LoginResponseSerializer> = _response
    private lateinit var loginRepository: LoginRepository

    fun authUser(login: String, password: String){
        val appComponent = MainApp().appComponent
        loginRepository = appComponent.getLoginRepository()
        viewModelScope.launch {
            _status.value = StatusValue.LOADING
            try {
                _response.value = loginRepository.authUser(LoginRequestSerializer(login, password))
                _status.value = StatusValue.SUCCESS
                addTokenToSharedPrefs(response.value!!.token)
                Log.d(LogTags.login, "Successful auth")
            } catch (e: Exception){
                Log.e(LogTags.login, "Error while auth")
            }
        }
    }

    private fun addTokenToSharedPrefs(token: String) {
        val sharedPreferences = Application().getSharedPreferences("token", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }
}