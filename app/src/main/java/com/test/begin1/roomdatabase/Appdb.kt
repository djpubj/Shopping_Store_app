package com.test.begin1.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.begin1.roomdatabase.Entity.Card
import com.test.begin1.roomdatabase.Entity.Cart
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = [Cart::class, Card::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class Appdb : RoomDatabase() {
    companion object {
        @Volatile
        var INSTANCE:Appdb?=null
        @OptIn(InternalCoroutinesApi::class)
        fun getdatabase(context: Context):Appdb{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    Appdb::class.java,
                    "App_dbb"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
        const val name = "App_dbb"
    }

    abstract fun getCardDao(): CardDao
    abstract fun getCartDao(): CartDao
}