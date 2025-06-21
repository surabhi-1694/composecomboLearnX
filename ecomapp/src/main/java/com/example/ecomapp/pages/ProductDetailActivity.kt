package com.example.ecomapp.pages

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import com.example.ecomapp.Home.CategoryWiseData
import com.example.ecomapp.databinding.ActivityProductDetailBinding
import com.example.ecomapp.pages.pageradapter.ImagepagerAdapter

class ProductDetailActivity : AppCompatActivity() {
   private  lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityProductDetailBinding.inflate(layoutInflater)

        val videoItemData = intent.getParcelableExtra<CategoryWiseData>("productITem")

        binding = ActivityProductDetailBinding.inflate(layoutInflater).apply {
            Log.e("videoItemData_id",videoItemData?.id.toString())
            videoItemData?.OtherDetails?.toString()?.let { Log.e("videoItemData ", it) }

            compOtherdetails.setContent {
                MaterialTheme {
                    OtherProductDetails(videoItemData)
                }
            }
        }
        setContentView(binding.main)
        setProductDetails(videoItemData)

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
            txtDescription.text = videoItemData?.description.plus(videoItemData?.description).plus(videoItemData?.description)
                .plus(videoItemData?.description).plus(videoItemData?.description).plus(videoItemData?.description)
            txtPrice.text = "\u20B9${videoItemData?.price}"
            txtActualPrice.apply {
                paint.isStrikeThruText = true
                text = "\u20B9${videoItemData?.actualPrice}"
            }
            viewPager.adapter = ImagepagerAdapter(videoItemData?.imageUrls as ArrayList)

            // Set up your ViewPager ...
            dotsIndicator.attachTo(viewPager)
//            TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        }

    }

}
