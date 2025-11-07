package com.example.bottomnavigationbar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bottomnavigationbar.databinding.ActivityLifecycleBinding

class LifecycleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLifecycleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLifecycleBinding.inflate(layoutInflater)

        val mainView = binding.root
        setContentView(mainView)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.e("LEfyCylce_ ", "Main_onCreate")
        binding.btnnext.setOnClickListener { view ->
            val intent = Intent(this, ActivityB::class.java)
            startActivity(
                intent
            )

        }

    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.e("LEfyCylce_ ", "Main_onNewIntent")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("LEfyCylce_ ", "Main_onSaveInstanceState")

    }

    override fun onStart() {
        super.onStart()
        Log.e("LEfyCylce_ ", "Main_onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.e("LEfyCylce_ ", "Main_onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.e("LEfyCylce_ ", "Main_onPause")

    }

    override fun onRestart() {
        super.onRestart()
        Log.e("LEfyCylce_ ", "Main_onRestart")

    }

    override fun onStop() {
        super.onStop()
        Log.e("LEfyCylce_ ", "Main_onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("LEfyCylce_ ", "Main_onDestroy")

    }
}