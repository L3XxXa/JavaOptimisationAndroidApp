package ru.nsu.malov.lab9.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.nsu.malov.lab9.databinding.LoginFragmentBinding
import ru.nsu.malov.lab9.log_tags.LogTags
import java.io.Console

class LoginFragment : Fragment() {
    private lateinit var loginFragmentBinding: LoginFragmentBinding
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
        return loginFragmentBinding.root
    }

    private fun authenticateUser() {
        Log.d(LogTags.login, "Clicked on login button")
        val login = loginFragmentBinding.editTextTextLogin
        val password = loginFragmentBinding.editTextTextPassword
    }
}