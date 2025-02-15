package com.example.android2o

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ScrollView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import java.util.Timer
import kotlin.concurrent.scheduleAtFixedRate


class MainActivityBottomLayout : AppCompatActivity() {
    private val TAG = "share intent"
    private var btnBottomFragment: AppCompatButton? =null
    private var btnBottomLayout: AppCompatButton? =null
    private var fragment_container:FrameLayout? =null

    private var scrollView:ScrollView?=null
    private var scrollTimer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        btnBottomFragment = findViewById(R.id.btnBottomFragment)
        btnBottomLayout = findViewById(R.id.btnBottomLayout)
        fragment_container = findViewById(R.id.fragment_container)

        scrollView = findViewById(R.id.autoScrollview)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //simple fragment to open
//        openfragment(DemoItemListFragment.newInstance(10))

//        handleimagefunction()

        btnBottomLayout?.setOnClickListener { v->
            Toast.makeText(this,"dfdfdfdf",Toast.LENGTH_LONG).show()
            fragment_container?.visibility = View.VISIBLE
            btnBottomLayout?.visibility = View.VISIBLE
            scrollTimer = Timer("scrollTimer", true)
            scrollTimer?.scheduleAtFixedRate(0, 20) {
                runOnUiThread() {
                    Log.e("scrollTIME_ ","scrollTIME")
                    scrollView?.scrollBy(0,1)
                }
            }

        }


        btnBottomFragment?.setOnClickListener { v->
            //Bottomsheetfragment dialog :)
            scrollTimer?.cancel()
            openBottomsheetfragment(newInstance = DemoItemListFragment.newInstance(10))
        }
    }

    private fun openBottomsheetfragment(newInstance: DemoItemListFragment) {
        newInstance.show(supportFragmentManager,"Bottomshet dialog")
    }

    private fun openfragment(fragment: Fragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
    }


    private fun handleimagefunction() {
        when {


            intent?.action == Intent.ACTION_SEND -> {
                 if (intent.type?.startsWith("image/") == true) {
                     println(TAG+"share intent open")
                     Toast.makeText(this,"abcd",Toast.LENGTH_LONG)
                }
            }

        }
    }
}