package ru.nsu.malov.lab9.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import ru.nsu.malov.lab9.databinding.ActivitySendMoneyBinding

class SendMoneyActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySendMoneyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sendMoney.setOnClickListener {
            sendMoney()
        }
        observer()
    }

    private fun observer() {
        TODO("Not yet implemented")
    }

    private fun sendMoney() {
        TODO("Not yet implemented")
    }
}