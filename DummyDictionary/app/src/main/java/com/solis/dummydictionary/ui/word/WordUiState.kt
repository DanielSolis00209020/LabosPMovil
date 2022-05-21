package com.solis.dummydictionary.ui.word

import androidx.lifecycle.LiveData
import com.solis.dummydictionary.data.model.Word

sealed class WordUiState {
    object Loading: WordUiState()
    class Error(val exception:Exception):WordUiState()
    data class Success(val word: LiveData<List<Word>>): WordUiState()
}