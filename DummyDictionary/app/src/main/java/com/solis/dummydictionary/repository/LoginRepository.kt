package com.solis.dummydictionary.repository

import com.solis.dummydictionary.network.ApiResponse
import com.solis.dummydictionary.network.WordService
import com.solis.dummydictionary.network.dto.LoginRequest
import retrofit2.HttpException
import java.io.IOException


class LoginRepository(private val api: WordService) {
    suspend fun login(username: String, password: String): ApiResponse<String>{
        try{
            val response = api.login(LoginRequest(username, password))
            return ApiResponse.Success(response.token)
        } catch (e:HttpException){
            if(e.code() == 400) {
                // TODO: convertir a obj kotlin
                /*return ApiResponse.ErrorWithMessage(e.response()?.body().toString())*/
                return ApiResponse.ErrorWithMessage("Error on login, verify credentials")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }
    }
}