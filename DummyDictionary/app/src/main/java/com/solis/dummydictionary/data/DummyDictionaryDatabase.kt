package com.solis.dummydictionary.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.solis.dummydictionary.data.dao.AntonymDao
import com.solis.dummydictionary.data.dao.SynonymDao
import com.solis.dummydictionary.data.dao.WordDao
import com.solis.dummydictionary.data.model.Antonym
import com.solis.dummydictionary.data.model.Synonym
import com.solis.dummydictionary.data.model.Word

@Database(
    entities = [Word::class, Antonym::class, Synonym::class],
    version = 1,
    exportSchema = false
)
abstract class DummyDictionaryDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun synonymDao(): SynonymDao
    abstract fun antonymDao(): AntonymDao

    companion object {
        @Volatile
        private var INSTANCE: DummyDictionaryDatabase? = null

        fun getInstance(context: Context): DummyDictionaryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DummyDictionaryDatabase::class.java,
                    "dic_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
