package com.example.android2o.gallerypick

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android2o.R
import com.example.android2o.databinding.ActivityGalleryBinding
import com.example.android2o.getImageDimensions
import java.util.UUID

class GalleryActivity : AppCompatActivity() {

    private lateinit var binding:ActivityGalleryBinding
    private var imageViewList:ArrayList<imageItem> = ArrayList<imageItem>()
    private lateinit var galleryAdapter :MyGalleryViewAdapter


    private lateinit var imageAdapter: ImageAdapter
    private val imageUris = ArrayList<imageItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

         val getContentLauncher = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
             if(::imageAdapter.isInitialized){
                 Log.e("androidPick_ ",uri.toString())
//                 onNewImagePicked(uri!!)
                 val item = imageItem(id = UUID.randomUUID().toString(),image = uri.toString(), originalWidth =  0, originalHeight =  0)
                 imageAdapter.addImage(item)

             }
        }


        imageAdapter = ImageAdapter(this, imageUris) { newHeight ->
            val params = binding.rlImage.layoutParams
            params.height = newHeight
            binding.rlImage.layoutParams = params
        }

        binding.rlImage.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rlImage.adapter = imageAdapter


        binding.btnImage.setOnClickListener {
            //open gallery pick and image get uri , display set height , width
            getContentLauncher.launch("image/*")
        }
    }






//    private fun setupView() {
//        val displayMetrics = resources.displayMetrics
//        val screenWidth = displayMetrics.widthPixels
//
//        galleryAdapter = MyGalleryViewAdapter(context = this@GalleryActivity, maxCellWidth = screenWidth/2,onCloseClick = { imageItem, position ->
//            Log.e("androidPick_remove ",imageViewList.size.toString())
//            //notifydat not working find alternative
//
//            onImageRemoved(position)
//
//        })
//        with(binding.rlImage) {
//
//            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
//            adapter = galleryAdapter
//            galleryAdapter.setNewImageList(imageViewList)
//            Log.e("IW_rlInit_H",binding.rlImage.height.toString())
//            Log.e("IW_rlInit_W",binding.rlImage.width.toString())
//        }
//
//    }




    /////////////////////// old pattern /////////////////////
//
//    fun updateGalleryHeight() {
//        val minHeight = imageViewList.minOfOrNull { it.originalHeight } ?: 0
//
//        // Update RecyclerView height
//        binding.rlImage.layoutParams.height = minHeight
//        binding.rlImage.requestLayout()
//
//        // Tell adapter to rebind with new target height
//        galleryAdapter.updateTargetHeight(minHeight)
//    }
//
//    fun onNewImagePicked(uri: Uri) {
//        val (w, h) = getImageDimensions(this, uri)
//        val item = imageItem(id = UUID.randomUUID().toString(),image = uri.toString(), originalWidth =  w, originalHeight =  h)
//        imageViewList.add(item)
//
//        galleryAdapter.setNewImageList(imageViewList)
////        galleryAdapter.updateTargetHeight(imageViewList.minOf { it.originalHeight }) // Optional
////        updateGalleryHeight()
//
//    }
//
//    fun onImageRemoved(position: Int) {
//        imageViewList.removeAt(position)
//
////        val newMinHeight = imageViewList.minOfOrNull { it.originalHeight } ?: 0
////        galleryAdapter.updateTargetHeight(newMinHeight)
//        galleryAdapter.setNewImageList(imageViewList)
////        updateGalleryHeight()
//
//        Log.e("IW_rl_H",binding.rlImage.height.toString())
//        Log.e("IW_rl_W",binding.rlImage.width.toString())
//
//    }
//    fun updateRecyclerViewHeight() {
//        val maxHeight = imageViewList.maxOfOrNull { it.originalHeight } ?: 0
//        if (maxHeight > 0) {
//            binding.rlImage.layoutParams = binding.rlImage.layoutParams.apply {
//                height = maxHeight
//            }
//            binding.rlImage.requestLayout()
//        }
//        Log.e("IW_rl_H",binding.rlImage.height.toString())
//        Log.e("IW_rl_W",binding.rlImage.width.toString())
//    }
}