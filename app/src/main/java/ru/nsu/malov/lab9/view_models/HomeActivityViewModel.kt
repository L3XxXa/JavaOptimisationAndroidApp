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
import ru.nsu.malov.lab9.R
import ru.nsu.malov.lab9.log_tags.LogTags
import ru.nsu.malov.lab9.network.StatusValue
import ru.nsu.malov.lab9.network.serializers.GetBalanceResponseSerializer

class HomeActivityViewModel: ViewModel() {
    private val _status = MutableLiveData<StatusValue>()
    val statusValue: LiveData<StatusValue> = _status
    private val _response = MutableLiveData<GetBalanceResponseSerializer>()
    val response: LiveData<GetBalanceResponseSerializer> = _response
    private lateinit var application: Application

    fun getBalance(application: MainApp){
        this.application = application
        val balanceRepository = application.appComponent.getBalanceRepository()
        _status.value = StatusValue.LOADING
        val token = getToken()
        if (token == ""){
            Log.e(LogTags.balance, "Error empty token")
            throw NullPointerException("Empty token")
        }
        Log.d(LogTags.balance, "Token is $token")
        viewModelScope.launch {
            try {
                _response.value = balanceRepository.getBalance(token)
                _status.value = StatusValue.SUCCESS
            } catch (e: Exception){
                _status.value = StatusValue.ERROR
                Log.e(LogTags.balance, "Error while getting balance ${e.message}")
            }
        }
    }

    fun getLogin(application: MainApp): String{
        val sharedPreferences = application.applicationContext.getSharedPreferences(application.resources.getString(
            R.string.app_name), Context.MODE_PRIVATE)
        return sharedPreferences.getString("login", "")!!
    }

    private fun getToken(): String {
        val sharedPreferences = application.applicationContext.getSharedPreferences(application.resources.getString(
            R.string.app_name), Context.MODE_PRIVATE)
        return sharedPreferences.getString("token", "")!!
    }
}