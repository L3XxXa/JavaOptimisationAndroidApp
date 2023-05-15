package ru.nsu.malov.lab9.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nsu.malov.lab9.network.StatusValue

class LoginViewModel : ViewModel(){
    private val _status = MutableLiveData<StatusValue>()
    private val status: LiveData<StatusValue> = _status

}