package ru.nsu.malov.lab9.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import ru.nsu.malov.lab9.MainApp
import ru.nsu.malov.lab9.R
import ru.nsu.malov.lab9.databinding.ActivityMainBinding
import ru.nsu.malov.lab9.log_tags.LogTags
import ru.nsu.malov.lab9.network.StatusValue
import ru.nsu.malov.lab9.view_models.LoginViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var activityMainBinding: ActivityMainBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        val loginButton = activityMainBinding.buttonLogin
        Log.d(LogTags.login, "$activityMainBinding asdasd")
        loginButton.setOnClickListener {
            authenticateUser()
        }
        observer()

    }

    private fun observer() {
        loginViewModel.status.observe(this) {
            when(it){
                StatusValue.SUCCESS -> {
                    Log.d(LogTags.login, "starting new activity")
                    startHomeActivity()
                }
                StatusValue.LOADING -> {
                    Log.d(LogTags.login, "Loading...")
                }
                StatusValue.ERROR -> {
                    Log.e(LogTags.login, "Error while auth ${loginViewModel.response}")
                    Toast.makeText(this, "Error!", Toast.LENGTH_LONG).show()
                }
                null -> {
                    Log.wtf(LogTags.login, "Ya hz)")
                    Toast.makeText(this, "WTF???", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun startHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun authenticateUser() {
        Log.d(LogTags.login, "Clicked on login button")
        val login = activityMainBinding.editTextTextLogin
        val password = activityMainBinding.editTextTextPassword
        val appComponent = (application as MainApp)
        loginViewModel.authUser(login.text.toString(), password.text.toString(), appComponent)

    }
}