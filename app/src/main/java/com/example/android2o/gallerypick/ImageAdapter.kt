package com.example.android2o.gallerypick

import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import coil3.load
import coil3.request.crossfade
import com.example.android2o.databinding.RawImageItemBinding

import com.example.android2o.placeholder.PlaceholderContent.PlaceholderItem
import com.example.android2o.databinding.RawItemBinding
import com.example.android2o.gallerypick.MyGalleryViewAdapter.ViewHolder

class ImageAdapter(
    private val context: Context,
    private val imageUris: ArrayList<imageItem>,
    private val onMaxHeightCalculated: (Int) -> Unit
) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private var commonHeight = 0
    private val imageSizes = mutableListOf<Pair<Int, Int>>() // width, height

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        return ViewHolder(
            RawImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = imageUris.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val uri = imageUris[position].image
        if (commonHeight == 0) return // Wait for measurement

        val (originalWidth, originalHeight) = imageSizes[position]

        // Scale image to fit common height
        val scaleFactor = originalHeight.toFloat() / commonHeight
        val targetWidth = (originalWidth / scaleFactor).toInt()

        holder.imageView.layoutParams = RecyclerView.LayoutParams(targetWidth, commonHeight)
        holder.imageView.setImageURI(Uri.parse(uri))

        holder.imgClose.setOnClickListener {
            removeImage(position)
        }
    }

    //new
    fun addImage(imageItem: imageItem) {
        Log.e("imageItem","Item")
        val inputStream = context.contentResolver.openInputStream(Uri.parse(imageItem.image))
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream?.close()

        if (bitmap != null) {
            imageSizes.add(bitmap.width to bitmap.height)
            imageUris.add(imageItem)
            recalculateCommonHeight()
        }
    }


    //new
    fun removeImage(index: Int) {
        if (index in imageUris.indices) {
            imageUris.removeAt(index)
            imageSizes.removeAt(index)
            recalculateCommonHeight()
        }
    }

     fun recalculateCommonHeight() {
        if (imageSizes.isEmpty()) {
            commonHeight = 0
            onMaxHeightCalculated(0)
            notifyDataSetChanged()
            return
        }

        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        val baseWidth = getTargetWidthForCount(imageSizes.size, screenWidth)

        commonHeight = imageSizes.maxOf { (w, h) ->
            val scaleFactor = w.toFloat() / baseWidth
            (h / scaleFactor).toInt()
        }
        onMaxHeightCalculated(commonHeight)
        notifyDataSetChanged()
    }

    private fun getTargetWidthForCount(count: Int, screenWidth: Int): Int {
        return when (count) {
            1 -> screenWidth
            2 -> screenWidth / 2
            3 -> screenWidth / 3
            else -> (screenWidth / 2.5).toInt()
        }
    }


    inner class ViewHolder(binding: RawImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageView: AppCompatImageView = binding.rawImage
        val imgClose: AppCompatImageView = binding.imgClose

    }
}


