package com.example.ecomapp.pages

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ecomapp.Home.CategoryWiseData
import com.example.ecomapp.R
import com.example.ecomapp.databinding.ActivityProductDetailBinding
import com.example.ecomapp.pages.pageradapter.ImagepagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.collections.ArrayList

class ProductDetailActivity : AppCompatActivity() {
   private  lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.main)
        val videoItemData = intent.getParcelableExtra<CategoryWiseData>("productITem")
        setProductDetails(videoItemData)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_product_detail)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets


        binding.imgFavourite.setOnClickListener{
            Log.e("imgFavourite ","imgFavourite")
            if(it.isSelected)
            it.isSelected = false
            else
                it.isSelected = true
        }
        }

    private fun setProductDetails(videoItemData: CategoryWiseData?) {
        binding.apply {
            txtTitle.text = videoItemData?.title
            txtDescription.text = videoItemData?.description
            txtPrice.text = "\u20B9${videoItemData?.price}"
            txtActualPrice.text = "\u20B9${videoItemData?.actualPrice}"
            viewPager.adapter = ImagepagerAdapter(videoItemData?.imageUrls as ArrayList)
            TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        }

    }

}
