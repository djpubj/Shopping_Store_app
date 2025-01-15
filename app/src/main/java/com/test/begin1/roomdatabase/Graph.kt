package com.test.begin1.roomdatabase

import android.content.Context
import com.test.begin1.roomdatabase.repository.Repository

object Graph {
    lateinit var db:Appdb
        private set

    val repository by lazy {
        Repository(
            cardDao = db.getCardDao(),
            cartDao = db.getCartDao()
        )
    }

    fun provide(context: Context){
        db= Appdb.getdatabase(context)
    }
}