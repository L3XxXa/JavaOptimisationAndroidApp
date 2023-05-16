package ru.nsu.malov.lab9.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.nsu.malov.lab9.databinding.LoginFragmentBinding
import ru.nsu.malov.lab9.log_tags.LogTags
import ru.nsu.malov.lab9.network.StatusValue
import ru.nsu.malov.lab9.view_models.LoginViewModel
import java.io.Console

class LoginFragment : Fragment() {
    private lateinit var loginFragmentBinding: LoginFragmentBinding
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginFragmentBinding = LoginFragmentBinding.inflate(inflater, container, false)
        val loginButton = loginFragmentBinding.buttonLogin
        loginButton.setOnClickListener {
            authenticateUser()
        }
        observer()
        return loginFragmentBinding.root
    }

    private fun observer() {
        loginViewModel.status.observe(viewLifecycleOwner) {
            when(it){
                StatusValue.SUCCESS -> {
                    Log.d(LogTags.login, "starting new fragment...")
                }
                StatusValue.LOADING -> {
                    Log.d(LogTags.login, "Loading...")
                }
                StatusValue.ERROR -> {
                    Log.e(LogTags.login, "Error while auth ${loginViewModel.response}")
                    Toast.makeText(context, "Error!", Toast.LENGTH_LONG).show()
                }
                null -> {
                    Log.wtf(LogTags.login, "Ya hz)")
                    Toast.makeText(context, "WTF???", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun authenticateUser() {
        Log.d(LogTags.login, "Clicked on login button")
        val login = loginFragmentBinding.editTextTextLogin
        val password = loginFragmentBinding.editTextTextPassword
        loginViewModel.authUser(login.text.toString(), password.text.toString())

    }
}