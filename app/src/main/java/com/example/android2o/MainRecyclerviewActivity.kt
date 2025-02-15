package com.example.android2o

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android2o.databinding.ActivityMainRecyclerviewBinding
import com.example.android2o.placeholder.ListAdapterItem
import java.util.UUID

class MainRecyclerviewActivity : AppCompatActivity() {



    private lateinit var  binding: ActivityMainRecyclerviewBinding
    /*
    * viewbinding
    * recyclerview
    * layoutmanager
    * list adapter object (reason why to use listadapter )
    * button to edit , delete ,
    * dummy list
    * */

//    private var rl_list:RecyclerView?=null
    private var nameArrayList = mutableListOf<ListAdapterItem>()
    private var linearLayoutManager:LinearLayoutManager?=null
    private var submitListAdapter:SubmitListRecyclerViewAdapter ? =null


    //viewmodel
//    private val testViewModel:TestViewModel by ViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainRecyclerviewBinding.inflate(layoutInflater)
        val mainView = binding.root
        setContentView(mainView)

        //viewmodel

        binding.rlSubmitlist.apply {
            linearLayoutManager = LinearLayoutManager(this@MainRecyclerviewActivity)

            submitListAdapter = SubmitListRecyclerViewAdapter( itemClickCallback ={pos->
                nameArrayList.removeAt(pos)
                submitListAdapter?.submitList(nameArrayList.toList())
                Log.e("LIST_SIZE_REMOVEONCLICK ",submitListAdapter?.itemCount.toString())
                Log.e("LIST_SIZE_REMOVE_position ", pos.toString())
            }, itemEditedCallback ={ item,position,updatedList->
                // Update the list with the edited name
                Log.e("UPdateSElection_List ",updatedList.toString())

            })
            adapter = submitListAdapter
            layoutManager = linearLayoutManager
            submitListAdapter?.submitList(createNameList())
        }

        binding.btnAdd.setOnClickListener { v ->
            nameArrayList.add(ListAdapterItem((nameArrayList.size-1).plus(1),UUID.randomUUID().toString()))
            submitListAdapter?.submitList(nameArrayList.toList())
            binding.rlSubmitlist.scrollToPosition(submitListAdapter?.itemCount!!)
            Log.e("LIST_SIZE",nameArrayList.size.toString() +" ITEM_CONT "+ submitListAdapter?.itemCount)
        }

        binding.btnDelete.setOnClickListener { v ->
            if(nameArrayList.size > 0){
                nameArrayList.removeLast()
                submitListAdapter?.submitList(nameArrayList.toList())
                Log.e("LIST_SIZE_remove",nameArrayList.size.toString() +" ITEM_CONT "+ submitListAdapter?.itemCount)

            }

        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun createNameList(): List<ListAdapterItem>? {
        for(i in 0  until 10){
            val listItem = ListAdapterItem(i,UUID.randomUUID().toString())
            nameArrayList.add(listItem)
        }
        return nameArrayList.toList()

    }
}