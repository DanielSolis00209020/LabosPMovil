package com.solis.dummydictionary.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.solis.dummydictionary.R
import com.solis.dummydictionary.repository.DictionaryRepository
import com.solis.dummydictionary.repository.LoginRepository
import com.solis.dummydictionary.ui.login.viewmodel.LoginViewModel
import com.solis.dummydictionary.ui.word.viewmodel.WordViewModel

class ViewModelFactory<R>(private val repository: R) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            return WordViewModel(repository as DictionaryRepository) as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository as LoginRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}