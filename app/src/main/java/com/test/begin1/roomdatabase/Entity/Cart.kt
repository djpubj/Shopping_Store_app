package com.test.begin1.roomdatabase.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "carts")
data class Cart(
    @PrimaryKey(autoGenerate = true)
    var id : Int=0,
    val name: String,
    val price: Double,
    var quantity: Int=0,
)
