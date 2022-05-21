package com.solis.dummydictionary.repository

import androidx.lifecycle.LiveData
import com.solis.dummydictionary.data.DummyDictionaryDatabase
import com.solis.dummydictionary.data.dao.AntonymDao
import com.solis.dummydictionary.data.dao.SynonymDao
import com.solis.dummydictionary.data.dao.WordDao
import com.solis.dummydictionary.data.model.Word
import com.solis.dummydictionary.network.ApiResponse
import com.solis.dummydictionary.network.WordService
import retrofit2.HttpException
import java.io.IOException

class DictionaryRepository(
    database: DummyDictionaryDatabase,
    private val api: WordService
) {
    private val wordDoa = database.wordDao()
    suspend fun getAllWords(): ApiResponse<LiveData<List<Word>>> {
        return try {
                val response = api.getAllWord()
                if (response.count > 0) {
                    wordDoa.insertWords(response.words)
                }
                ApiResponse.Success(data = wordDoa.getAllWords())
            } catch (e: HttpException) {
                ApiResponse.Error(exception = e)
            } catch (e: IOException) {
                ApiResponse.Error(exception = e)
            }
    }

    suspend fun addWord(word: Word) {
        wordDoa.addWord(word)
    }
}