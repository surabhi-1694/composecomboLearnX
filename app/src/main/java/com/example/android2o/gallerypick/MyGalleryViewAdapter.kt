package com.example.android2o.gallerypick

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
class MyGalleryViewAdapter(val onCloseClick:(imageItem:imageItem,position:Int)->Unit,
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = imageViewList[position]
        //uri ,string, conversion
        holder.imgView.setImageURI(Uri.parse(item.image))
        holder.imgClose.setOnClickListener {
            onCloseClick(item,position)
        }

    }


    override fun getItemCount(): Int = imageViewList.size

    fun setNewImageList(newimageViewList: ArrayList<imageItem>) {
//            values.clear()
//        values.addAll(imageViewList)
//        notifyDataSetChanged()

        Log.d("BeforeClear", "newList size: ${imageViewList.size}") // ← still has data?
        imageViewList.clear()
        Log.d("AfterClear", "newList size: ${imageViewList.size}") // ← becomes 0? means shared ref
        imageViewList.addAll(newimageViewList)
        notifyDataSetChanged()

    }

    inner class ViewHolder(binding: RawImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgView: AppCompatImageView = binding.rawImage
        val imgClose: AppCompatImageView = binding.imgClose

    }

}