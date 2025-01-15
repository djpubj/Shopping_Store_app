package com.test.begin1.roomdatabase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class Converter {
    @TypeConverter
    fun fromBitmap(//compressImageToFixedSize
        bitmap: Bitmap,
    ): ByteArray {
        val outputStream = ByteArrayOutputStream()
        val maxSizeKB=600 // Desired size in kilobytes
        // Step 1: Resize the Bitmap
        val targetWidth = 800 // Example: Fixed width
        val aspectRatio = bitmap.height.toFloat() / bitmap.width.toFloat()
        val targetHeight = (targetWidth * aspectRatio).toInt()
        val resizedBitmap = Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, true)

        // Step 2: Compress the Bitmap
        var quality = 100
        resizedBitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)

        // Step 3: Check and Adjust File Size
        while (outputStream.toByteArray().size / 1024 > maxSizeKB && quality > 10) {
            outputStream.reset() // Clear the stream
            quality -= 5 // Decrease quality by 5
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        }

        return outputStream.toByteArray()
    }


    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}