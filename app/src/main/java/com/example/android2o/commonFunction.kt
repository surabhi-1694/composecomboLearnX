package com.example.android2o

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri


fun getImageSizeFromUri(context: Context, uri: Uri): Pair<Int, Int> {
    val options = BitmapFactory.Options().apply {
        inJustDecodeBounds = true
    }
    context.contentResolver.openInputStream(uri).use {
        BitmapFactory.decodeStream(it, null, options)
    }
    return Pair(options.outWidth, options.outHeight)
}

fun getImageDimensions(context: Context, uri: Uri): Pair<Int, Int> {
    val options = BitmapFactory.Options().apply { inJustDecodeBounds = true }
    context.contentResolver.openInputStream(uri)?.use {
        BitmapFactory.decodeStream(it, null, options)
    }
    return Pair(options.outWidth, options.outHeight)
}
