package ru.nsu.malov.lab9.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import ru.nsu.malov.lab9.MainApp
import ru.nsu.malov.lab9.R
import ru.nsu.malov.lab9.databinding.ActivitySendMoneyBinding
import ru.nsu.malov.lab9.databinding.DialogCodeBinding
import ru.nsu.malov.lab9.log_tags.LogTags
import ru.nsu.malov.lab9.network.StatusValue
import ru.nsu.malov.lab9.view_models.SendMoneyViewModel

class SendMoneyActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySendMoneyBinding
    private val sendMoneyViewModel: SendMoneyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sendMoney.setOnClickListener {
            sendMoney()
        }
        observer()
        sendCodeObserver()
    }

    private fun observer() {
        sendMoneyViewModel.status.observe(this){
            when (it){
                StatusValue.ERROR -> {
                    Log.e(LogTags.sendMoney, "Error while sending money ${sendMoneyViewModel.response}")
                }
                StatusValue.SUCCESS -> {
                    Log.d(LogTags.sendMoney, "Code is ${sendMoneyViewModel.response.value!!.code}")
                    DialogCodeFragment(sendMoneyViewModel).show(supportFragmentManager, "Code")
                }
                StatusValue.LOADING -> {
                    Log.d(LogTags.balance, "Loading...")
                }
                else -> {
                    Log.wtf(LogTags.balance, "Ya hz)")
                }
            }
        }
    }

    private fun sendCodeObserver() {
        sendMoneyViewModel.statusCode.observe(this){
            when (it){
                StatusValue.ERROR -> {
                    Log.e(LogTags.sendMoney, "Error while sending code")
                    Toast.makeText(this, "Error code", Toast.LENGTH_SHORT).show()
                }
                StatusValue.SUCCESS -> {
                    Toast.makeText(this, "Отправлено!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
                StatusValue.LOADING -> {
                    Log.d(LogTags.balance, "Loading...")
                }
                else -> {
                    Log.wtf(LogTags.balance, "Ya hz)")
                }
            }
        }
    }

    private fun sendMoney() {
        val app = application as MainApp
        val amount = binding.amount.text.toString().toInt()
        val toWhom = binding.toWhom.text.toString()
        sendMoneyViewModel.sendMoney(app, toWhom, amount)
    }
}