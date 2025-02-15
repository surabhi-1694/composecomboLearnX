package com.example.bottomnavigationbar

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.bottomnavigationbar.databinding.ActivityCorutineMainBinding
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

class CorutineMainActivity : AppCompatActivity() {
    lateinit var binding :  ActivityCorutineMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCorutineMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_corutine_main)

        binding.btnAsyncAwait.setOnClickListener {
            callAsyncAwaitFun()
        }

        binding.btnSuspend.setOnClickListener {
//    check suspend funcions for below scenarios
//    create two network call
//    print 2 statments in both of them
//    make one a suspend fucntion ,put delay ,  check how secod function print first before 1st function task complete ?
//    check how network call and display dat work ?
//    check whicch one called first why suspend function
            lifecycleScope.launch {
                callMainSuspendFun()
            }

        }

        binding.btnSuspendYield.setOnClickListener {
            lifecycleScope.launch {
                longRunningLoop()
            }
        }
    }

    private  fun callMainSuspendFun() {
        lifecycleScope.launch {
            //first network call suspend function
            firstNetworkSuspendFun()
            seconfNetworkSuspendFun()
        }

        }


    private suspend fun seconfNetworkSuspendFun() {
        Log.e("SUSPEND_ ","Second Suspend Start")
        delay(2000)
        Log.e("SUSPEND_ ","Second Suspend Delay")
    }

    private suspend fun firstNetworkSuspendFun() {
//        lifecycleScope.launch {
            Log.e("SUSPEND_ ","First Suspend Start")
            delay(6000)
            Log.e("SUSPEND_ ","First Suspend Delay")
//        }
    }

    suspend fun longRunningLoop() {
        for (i in 1..5) {
            Log.e("YIELD","Start")
            println("Processing item $i")
            yield()  // Suspension point
            Log.e("YIELD","AFTER Yield")

        }
    }


    //// Async Await Functions

    private fun callAsyncAwaitFun() {
        lifecycleScope.launch {
            coroutineFSuspendunction()
            simplePrintFunction()
        }
    }
    private fun simplePrintFunction() {
        Log.e("Async", " _After Async Result")
    }
    private  fun coroutineFunction(): String {
        var result = ""
        lifecycleScope.launch {
           result  = async{
               Log.e("Async", " _Result Await")

               delay(100000)
           }.await().toString()
        }
        return result

    }
    private suspend fun coroutineFSuspendunction() {
            lifecycleScope.async{
                Log.e("Async", " _Result Await")
                delay(5000)
            }.await()
//        simplePrintFunction()
    }
/// Async Await function

}