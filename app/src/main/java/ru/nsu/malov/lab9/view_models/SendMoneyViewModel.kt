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

    private val _statusCode = MutableLiveData<StatusValue>()
    val statusCode: LiveData<StatusValue> = _statusCode
    private val _responseCode = MutableLiveData<String>()
    val responseCode: LiveData<String> = _responseCode

    private lateinit var application: MainApp
    private lateinit var sendMoneyRepository: SendMoneyRepository
    private lateinit var sendMoneyRequestSerializer: SendMoneyRequestSerializer
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
        sendMoneyRequestSerializer = SendMoneyRequestSerializer(toWhom, amount)
        viewModelScope.launch {
            try {
                _response.value = sendMoneyRepository.sendMoney(sendMoneyRequestSerializer, token)
                code = response.value!!.code
                _status.value = StatusValue.SUCCESS
            } catch (e: Exception){
                _status.value = StatusValue.ERROR
                Log.e(LogTags.sendMoney, "Error while sending money. ${e.printStackTrace()}")
            }
        }
    }

    fun sendCode(string: Int){
        val balanceRepository = application.appComponent.getSendMoneyRepository()
        _statusCode.value = StatusValue.LOADING
        val token = getToken()
        if (string != code){
            _statusCode.value = StatusValue.ERROR
            Log.e(LogTags.balance, "Error while getting balance code")

        }
        if (token == ""){
            Log.e(LogTags.sendMoney, "Error empty token")
            throw NullPointerException("Empty token")
        }
        Log.d(LogTags.sendMoney, "Token is $token")
        viewModelScope.launch {
            try {
                _responseCode.value = balanceRepository.sendCode(sendMoneyRequestSerializer, token)
                _statusCode.value = StatusValue.SUCCESS
            } catch (e: Exception){
                _statusCode.value = StatusValue.ERROR
                Log.e(LogTags.balance, "Error while sending code ${e.printStackTrace()}")
            }
        }
    }

    private fun getToken(): String {
        val sharedPreferences = application.applicationContext.getSharedPreferences(application.resources.getString(
            R.string.app_name), Context.MODE_PRIVATE)
        return sharedPreferences.getString("token", "")!!
    }
}