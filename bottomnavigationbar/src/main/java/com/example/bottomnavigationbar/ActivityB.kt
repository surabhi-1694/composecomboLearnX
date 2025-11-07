package com.example.bottomnavigationbar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bottomnavigationbar.databinding.ActivityBBinding

class ActivityB : AppCompatActivity() {

    private lateinit var binding: ActivityBBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        setContentView(R.layout.activity_b)
        binding = ActivityBBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.e("LEfyCylce_ ", "MainB_onCreate")

        binding.btnnextC.setOnClickListener {
            val intent = Intent(this, ActivityC::class.java)
            startActivity(intent)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.e("LEfyCylce_ ", "MainB_onNewIntent")

    }


    override fun onStart() {
        super.onStart()
        Log.e("LEfyCylce_ ", "MainB_onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.e("LEfyCylce_ ", "MainB_onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.e("LEfyCylce_ ", "MainB_onPause")

    }

    override fun onRestart() {
        super.onRestart()
        Log.e("LEfyCylce_ ", "MainB_onRestart")

    }

    override fun onStop() {
        super.onStop()
        Log.e("LEfyCylce_ ", "MainB_onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("LEfyCylce_ ", "MainB_onDestroy")

    }
}