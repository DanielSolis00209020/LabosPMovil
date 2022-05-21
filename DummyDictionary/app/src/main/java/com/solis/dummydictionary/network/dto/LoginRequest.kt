package com.solis.dummydictionary.network.dto

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("username")
    val username: String,
    val password: String
)