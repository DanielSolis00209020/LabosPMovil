package com.solis.dummydictionary.ui.login

sealed class LoginUiStatus {
    object Resume: LoginUiStatus()
    object Loading: LoginUiStatus()
    class Error(val exception: Exception): LoginUiStatus()
    class ErrorWithMessage(val message: String): LoginUiStatus()
    data class Success(val token: String): LoginUiStatus()
}