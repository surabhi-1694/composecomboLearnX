package com.example.android2o.gallerypick

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import coil3.load
import coil3.request.crossfade
import com.example.android2o.databinding.RawImageItemBinding

import com.example.android2o.placeholder.PlaceholderContent.PlaceholderItem
import com.example.android2o.databinding.RawItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyGalleryViewAdapter(val context:Context,private var maxCellWidth: Int,
                           val onCloseClick:(imageItem:imageItem,position:Int)->Unit,
) : RecyclerView.Adapter<MyGalleryViewAdapter.ViewHolder>() {
    private val imageViewList: ArrayList<imageItem> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            RawImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    fun updateTargetHeight(newHeight: Int) {
//        targetHeight = newHeight
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = imageViewList[position]

        // Calculate scaled width using original aspect ratio
//        val ratio = item.originalWidth.toFloat() / item.originalHeight
//        val scaledWidth = (targetHeight * ratio).toInt()
//
//        with(holder.imgView) {
//            layoutParams = layoutParams.apply {
//                width = scaledWidth
//                height = targetHeight
//            }
//            setImageURI(null) // clear recycled image
//            setImageURI(Uri.parse(item.image))
//        }
        // Use maxCellWidth or original width (whichever is smaller)
//        val displayWidth = maxCellWidth
//        val displayHeight = (displayWidth * item.originalHeight / item.originalWidth.toFloat()).toInt()
//
//        holder.imgView.layoutParams = holder.imgView.layoutParams.apply {
//            width = displayWidth
//            height = displayHeight
//        }


        val displayWidth = context.resources.displayMetrics.widthPixels

        val rawWidth = item.originalWidth.toFloat()
        val rawHeight = item.originalHeight.toFloat()

        val aspectRatio = rawHeight / rawWidth
        var displayHeight = (displayWidth * aspectRatio).toInt()

// Clamp to reasonable screen height (e.g., 80% of screen height)
        val screenHeight = context.resources.displayMetrics.heightPixels
        val maxAllowedHeight = (screenHeight * 0.8).toInt()

        if (displayHeight > maxAllowedHeight) {
            // Scale down proportionally to fit within screen height
            val scale = maxAllowedHeight.toFloat() / displayHeight
            displayHeight = maxAllowedHeight
            // Optionally reduce width as well to preserve ratio
            val scaledWidth = (displayWidth * scale).toInt()

            holder.imgView.layoutParams = holder.imgView.layoutParams.apply {
                width = scaledWidth
                height = displayHeight
            }
        } else {
            // Use full width
            holder.imgView.layoutParams = holder.imgView.layoutParams.apply {
                width = displayWidth
                height = displayHeight
            }
        }

        holder.imgView.setImageURI(null)
        holder.imgView.setImageURI(Uri.parse(item.image))


        holder.imgView.post {
            holder.imgView.apply {
                Log.e("IW_Adapter_H",height.toString())
                Log.e("IW_Adapter_W",width.toString())
            }
        }

        holder.imgClose.setOnClickListener {
            onCloseClick(item,position)
        }
    }


    override fun getItemCount(): Int = imageViewList.size

    fun setNewImageList(newimageViewList: ArrayList<imageItem>) {
        imageViewList.clear()
        imageViewList.addAll(newimageViewList)

        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: RawImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgView: AppCompatImageView = binding.rawImage
        val imgClose: AppCompatImageView = binding.imgClose

    }

}