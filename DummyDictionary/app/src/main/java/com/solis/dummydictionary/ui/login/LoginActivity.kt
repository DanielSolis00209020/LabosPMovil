package com.solis.dummydictionary.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.solis.dummydictionary.DummyDictionaryApplication
import com.solis.dummydictionary.MainActivity
import com.solis.dummydictionary.R
import com.solis.dummydictionary.databinding.ActivityLoginBinding
import com.solis.dummydictionary.ui.ViewModelFactory
import com.solis.dummydictionary.ui.login.viewmodel.LoginViewModel
import java.util.*
import kotlin.concurrent.schedule

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    val app by lazy {
        application as DummyDictionaryApplication
    }
    private val viewModelFactory by lazy {
        ViewModelFactory(app.getLoginRepository())
    }
    private val viewModel: LoginViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (app.isUserLogin()) {
            return startMainActivity()
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel

        viewModel.status.observe(this){ status ->
            handleUiState(status)
        }
    }

    private fun handleUiState(status: LoginUiStatus) {
        when (status) {
            is LoginUiStatus.Error -> Log.d("Login list status", "Error")
            is LoginUiStatus.ErrorWithMessage -> {
                // To see the progressBar
                Timer().schedule(500){
                    binding.progressBarLogin.visibility = View.INVISIBLE
                    Snackbar.make(binding.imageViewLogin, status.message, Snackbar.LENGTH_LONG).show()
                }
            }
            LoginUiStatus.Loading -> {binding.progressBarLogin.visibility = View.VISIBLE
                Log.d("Login List Status", "Loading")}
            LoginUiStatus.Resume -> {
                binding.progressBarLogin.visibility = View.INVISIBLE
                Log.d("Login List Status","Resume")}
            is LoginUiStatus.Success -> {
                binding.progressBarLogin.visibility = View.INVISIBLE
                app.saveAuthToken(status.token)
                startMainActivity()
            }
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}