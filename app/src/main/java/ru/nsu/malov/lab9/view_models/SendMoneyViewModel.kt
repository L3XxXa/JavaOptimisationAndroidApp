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
import ru.nsu.malov.lab9.network.serializers.SendMoneyRequestSerializer
import ru.nsu.malov.lab9.network.serializers.SendMoneyResponseSerializer
import ru.nsu.malov.lab9.repository.SendMoneyRepository
import java.lang.Exception

class SendMoneyViewModel : ViewModel() {
    private val _status = MutableLiveData<StatusValue>()
    val status: LiveData<StatusValue> = _status
    private val _response = MutableLiveData<SendMoneyResponseSerializer>()
    val response: LiveData<SendMoneyResponseSerializer> = _response
    private lateinit var application: MainApp
    private lateinit var sendMoneyRepository: SendMoneyRepository
    private var code = 0

    fun sendMoney(application: MainApp, toWhom: String, amount: Int){
        sendMoneyRepository = application.appComponent.getSendMoneyRepository()
        this.application = application
        _status.value = StatusValue.LOADING
        val token = getToken()
        if (token == ""){
            Log.e(LogTags.sendMoney, "Token is empty")
            throw NullPointerException("token is empty")
        }
        viewModelScope.launch {
            try {
                _response.value = sendMoneyRepository.sendMoney(SendMoneyRequestSerializer(toWhom, amount), token)
                code = response.value!!.code
                _status.value = StatusValue.SUCCESS
            } catch (e: Exception){
                _status.value = StatusValue.ERROR
                Log.e(LogTags.sendMoney, "Error while sending money. ${e.printStackTrace()}")
            }
        }
    }

    private fun getToken(): String {
        val sharedPreferences = application.applicationContext.getSharedPreferences(application.resources.getString(
            R.string.app_name), Context.MODE_PRIVATE)
        return sharedPreferences.getString("token", "")!!
    }
}