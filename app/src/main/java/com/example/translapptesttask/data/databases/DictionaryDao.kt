package com.example.translapptesttask.data.databases

import androidx.room.Dao
import androidx.room.DeleteTable
import androidx.room.Insert
import androidx.room.Query
import com.example.translapptesttask.data.net.respmodels.TranslatedEntity

@Dao
interface DictionaryDao {
    @Query("SELECT*FROM dictionary")
    fun getAllFavourites(): List<TranslatedEntity>

    @Query("SELECT (CASE WHEN EXISTS(SELECT 1 FROM dictionary" +
            "    WHERE  text= :word) THEN 1\n" +
            "    ELSE 0 END)")
    fun isWordInDictionary(word: String): Boolean

    @Insert(entity = TranslatedEntity::class)
    fun addToDictionaryTable(entity: TranslatedEntity)

    @Query("SELECT*FROM dictionary WHERE id= :id")
    fun findById(id: Int): TranslatedEntity

    @Query("SELECT COUNT(*) FROM dictionary")
    fun getWordsCount(): Int

    @Query("DELETE FROM dictionary")
    fun dropDictTable()
}