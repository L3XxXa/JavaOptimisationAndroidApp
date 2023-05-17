package ru.nsu.malov.lab9.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.nsu.malov.lab9.MainApp
import ru.nsu.malov.lab9.databinding.ActivityHomeBinding
import ru.nsu.malov.lab9.log_tags.LogTags
import ru.nsu.malov.lab9.network.StatusValue
import ru.nsu.malov.lab9.view_models.HomeActivityViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sendMoney.setOnClickListener {
            sendMoney()
        }
        observer()
        getMoney()
        getLogin()
    }

    private fun getLogin() {
        val login = viewModel.getLogin(application as MainApp)
        binding.login.text = login
    }

    private fun getMoney() {
        viewModel.getBalance((application as MainApp))
    }

    private fun observer() {
        viewModel.statusValue.observe(this){
            when (it){
                StatusValue.ERROR -> {
                    Log.e(LogTags.balance, "Error while getting balance ${viewModel.response}")
                }
                StatusValue.SUCCESS -> {
                    Log.d(LogTags.balance, "Balance is ${viewModel.response.value!!.balance}")
                    binding.moneyAmount.text = viewModel.response.value!!.balance.toString()
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
        val intent = Intent(this, SendMoneyActivity::class.java)
        startActivity(intent)
    }
}