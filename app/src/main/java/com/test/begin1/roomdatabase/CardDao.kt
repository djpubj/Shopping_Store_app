package com.test.begin1.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.begin1.roomdatabase.Entity.Card
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Query("select * from cards")
    fun getallCards(): Flow<List<Card>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCard(card: Card)

    @Query("SELECT * FROM cards WHERE title LIKE  '%' || :title || '%' COLLATE NOCASE")
    fun searchbytitle(title: String): Flow<List<Card>>

    @Query("delete from cards where id=:id")
    suspend fun deleteCard(id: Int)
}