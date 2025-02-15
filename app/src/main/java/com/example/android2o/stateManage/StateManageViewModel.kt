package com.example.android2o.stateManage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StateManageViewModel:ViewModel() {

    private val _firstName = MutableLiveData<String>()
    val firstName : LiveData<String>  = _firstName

    fun onFirstNameUpdate(name:String){
        _firstName.value = name

    }

}