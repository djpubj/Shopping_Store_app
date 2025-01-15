package com.test.begin1.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.test.begin1.roomdatabase.Entity.Cart
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("select * from carts")
    fun getallitems(): Flow<List<Cart>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(item: Cart)

    @Transaction
    suspend fun addOrUpdateItem(item: Cart) {
        val item = getItemById(item.id) ?: item
        item.quantity += 1
        insertOrUpdate(item)
    }
    @Query("SELECT * FROM carts WHERE id = :id LIMIT 1")
    suspend fun getItemById(id: Int): Cart?

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun additem(item: Cart)

    @Query("delete from carts where id =:id")
    suspend fun deleteitem(id: Int)

}