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
import com.example.android2o.R
import com.example.android2o.databinding.ActivityGalleryBinding
import java.util.UUID

class GalleryActivity : AppCompatActivity() {
    private lateinit var binding:ActivityGalleryBinding
    private var imageViewList:ArrayList<imageItem> = ArrayList<imageItem>()
    private lateinit var galleryAdapter :MyGalleryViewAdapter



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
             Log.e("androidPick_ ",uri.toString())
             val imageItem = imageItem(image = uri.toString(),id = UUID.randomUUID().toString())
             imageViewList.add(imageItem)
             Log.e("androidPick_listSIze ",imageViewList.size.toString())
             if(::galleryAdapter.isInitialized){
                 galleryAdapter.setNewImageList(imageViewList)
             }

        }
        setupView()
        binding.btnImage.setOnClickListener {
            //open gallery pick and image get uri , display set height , width
            getContentLauncher.launch("image/*")
        }


    }

    private fun setupView() {
        galleryAdapter = MyGalleryViewAdapter(onCloseClick = { imageItem, position ->
            imageViewList.remove(imageItem)
            Log.e("androidPick_remove ",imageViewList.size.toString())
            //notifydat not working find alternative
            galleryAdapter.notifyDataSetChanged()

        })
        with(binding.rlImage) {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter = galleryAdapter
            galleryAdapter.setNewImageList(imageViewList)
        }

    }
}