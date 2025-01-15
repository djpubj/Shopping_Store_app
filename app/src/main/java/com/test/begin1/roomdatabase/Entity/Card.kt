package com.test.begin1.roomdatabase.Entity

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cards")
data class Card(
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,

    var bitmap: Bitmap? = null, // Placeholder empty image
    var title: String = "",
    var discription: String = "",
    var price: Double = 0.0

)
