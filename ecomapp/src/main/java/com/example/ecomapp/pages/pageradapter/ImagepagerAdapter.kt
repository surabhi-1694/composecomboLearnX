package com.example.ecomapp.pages.pageradapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil3.load
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import com.example.ecomapp.databinding.GalleryItemviewBinding

class ImagepagerAdapter(private val imageUrls:ArrayList<String>) :RecyclerView.Adapter<ImagepagerAdapter.ViewHolder>() {

    inner class ViewHolder(binding: GalleryItemviewBinding):RecyclerView.ViewHolder(binding.root){
        val imageView:AppCompatImageView = binding.imgGalleryitem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = GalleryItemviewBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)

//        val imageView = ImageView(parent.context).apply {
//            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
//            scaleType = ImageView.ScaleType.CENTER_INSIDE
//        }

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
      return  imageUrls.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.load(imageUrls[position]){

        }

    }

}