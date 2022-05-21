package com.solis.dummydictionary.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.solis.dummydictionary.data.model.Antonym
import com.solis.dummydictionary.data.model.Word

@Dao
interface AntonymDao {

    @Query("SELECT w.term, w.definition, w.is_fav FROM antonym_table as at INNER JOIN word_table as w ON w.term == at.antonym_term WHERE at.term = :term")
    fun getAllAntonym(term: String): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(word: Antonym)

    @Update
    fun updateWord(word: Antonym)

    @Delete
    fun deleteWord(word: Antonym)
}