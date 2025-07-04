package com.example.ecomapp.pages

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ecomapp.Home.CategoryWiseData
import com.example.ecomapp.R
import com.example.ecomapp.databinding.ActivityProductDetailBinding
import com.example.ecomapp.pages.pageradapter.ImagepagerAdapter
import com.example.ecomapp.utils.addToCart

class ProductDetailActivity : AppCompatActivity() {
   private  lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
// Enable edge-to-edge rendering
//        WindowCompat.setDecorFitsSystemWindows(window, false)

        val videoItemData = intent.getParcelableExtra<CategoryWiseData>("productITem")

        binding = ActivityProductDetailBinding.inflate(layoutInflater).apply {
            Log.e("videoItemData_id",videoItemData?.id.toString())
            videoItemData?.otherDetails?.toString()?.let { Log.e("videoItemData ", it) }

            compOtherdetails.setContent {
                MaterialTheme {
                    OtherProductDetails(videoItemData)
                }
            }
        }
        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setProductDetails(videoItemData)

        binding.imgFavourite.setOnClickListener{
            Log.e("imgFavourite ","imgFavourite")
            if(it.isSelected)
            it.isSelected = false
            else
                it.isSelected = true
        }
        }

    private fun setProductDetails(categoryWiseData: CategoryWiseData?) {
        binding.apply {
            txtTitle.text = categoryWiseData?.title
            txtDescription.text = categoryWiseData?.description.plus(categoryWiseData?.description).plus(categoryWiseData?.description)
                .plus(categoryWiseData?.description).plus(categoryWiseData?.description).plus(categoryWiseData?.description)
            txtPrice.text = "\u20B9${categoryWiseData?.price}"
            txtActualPrice.apply {
                paint.isStrikeThruText = true
                text = "\u20B9${categoryWiseData?.actualPrice}"
            }
            viewPager.adapter = ImagepagerAdapter(categoryWiseData?.imageUrls as ArrayList)
            btnAddToCart.setOnClickListener{
                addToCart(this@ProductDetailActivity,categoryWiseData.id)
            }

            // Set up your ViewPager ...
            dotsIndicator.attachTo(viewPager)
//            TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        }

    }

}
