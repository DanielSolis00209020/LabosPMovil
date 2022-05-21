package com.solis.dummydictionary.ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solis.dummydictionary.network.ApiResponse
import com.solis.dummydictionary.repository.LoginRepository
import com.solis.dummydictionary.ui.login.LoginUiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {
    val userField = MutableLiveData("")
    val passwordField = MutableLiveData("")

    private val _status = MutableLiveData<LoginUiStatus>(LoginUiStatus.Resume)
    val status: LiveData<LoginUiStatus>
        get() = _status

    fun onLogin() {
        _status.value = LoginUiStatus.Loading

        viewModelScope.launch(Dispatchers.IO) {
            _status.postValue(
                when (val response = repository.login(
                    userField.value.toString(),
                    passwordField.value.toString()
                )) {
                    is ApiResponse.Error -> LoginUiStatus.Error(response.exception)
                    // is ApiResponse.ErrorWithMessage -> LoginUiStatus.Resume
                        // LoginUiStatus.Error(Exception(response.message))
                    // TODO: modificar estado para permitir errores con mensaje
                    is ApiResponse.ErrorWithMessage -> LoginUiStatus.ErrorWithMessage(response.message)
                    is ApiResponse.Success -> LoginUiStatus.Success(response.data)
                }
            )
        }
    }

}