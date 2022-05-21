package com.solis.dummydictionary.ui.word.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solis.dummydictionary.data.model.Word
import com.solis.dummydictionary.network.ApiResponse
import com.solis.dummydictionary.repository.DictionaryRepository
import com.solis.dummydictionary.ui.word.WordUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(private val repository: DictionaryRepository): ViewModel() {
    private val _status = MutableLiveData<WordUiState>(WordUiState.Loading)
    val status: LiveData<WordUiState>
        get() = _status

    fun getAllWords() {
        _status.value = WordUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _status.postValue(
                when (val response = repository.getAllWords()) {
                    is ApiResponse.Error -> WordUiState.Error(response.exception)
                    is ApiResponse.Success -> WordUiState.Success(response.data)
                    is ApiResponse.ErrorWithMessage -> TODO()
                }
            )
        }
    }

    fun addWord(word: Word) {
        viewModelScope.launch {
            repository.addWord(word)
        }
    }
}