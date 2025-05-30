package com.example.ecomapp.pages.pageradapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil3.load
import coil3.request.transformations
import coil3.transform.CircleCropTransformation

class ImagepagerAdapter(val imageUrls:ArrayList<String>) :RecyclerView.Adapter<ImagepagerAdapter.ViewHolder>() {

    inner class ViewHolder(val imageView: ImageView ):RecyclerView.ViewHolder(imageView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val imageView = ImageView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
            scaleType = ImageView.ScaleType.CENTER_INSIDE
        }
        return ViewHolder(imageView)
    }

    override fun getItemCount(): Int {
      return  imageUrls.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.load(imageUrls[position]){

        }

    }

}