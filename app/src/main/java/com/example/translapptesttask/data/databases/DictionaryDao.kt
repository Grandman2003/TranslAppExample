package com.example.translapptesttask.data.databases

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.core_app_api.models.TranslatedEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface DictionaryDao {
    @Query("SELECT*FROM dictionary")
    fun getAllWords(): Single<List<TranslatedEntity>>

    @Query(
        "SELECT (CASE WHEN EXISTS(SELECT 1 FROM dictionary" +
            "    WHERE  text= :word) THEN 1\n" +
            "    ELSE 0 END)"
    )
    fun isWordInDictionary(word: String): Single<Boolean>

    @Insert(entity = TranslatedEntity::class)
    fun addToDictionaryTable(entity: TranslatedEntity)

    @Query("SELECT*FROM dictionary WHERE id= :id")
    fun findById(id: Int): Single<TranslatedEntity>

    @Query("SELECT COUNT(*) FROM dictionary")
    fun getWordsCount(): Single<Int>

    @Query("DELETE FROM dictionary")
    fun dropDictTable(): Completable

    @Query("SELECT*FROM dictionary WHERE text= :text")
    fun findWordInDictionary(text: String): Single<TranslatedEntity>

    @Query("UPDATE dictionary SET isFavourite= :isFavourite WHERE text= :text ")
    fun updateFavouriteState(text: String, isFavourite: Boolean)
}
