package com.example.ecomapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
//here we are creating viewmodel with AndroidViewModel
*  so that we can use application as context
*  since we are creating common model class to manage getter setter for Pref. we need context to be passed in for DataStoreInstance
* */
@HiltViewModel
class DataStoreModel @Inject constructor(application: Application):
    AndroidViewModel(application = application) {

init {
    getPrefBadgeCount()
}


    private val _intBadgeValue = MutableStateFlow<Int?>(null)
    val intBadgeValue:StateFlow<Int?> = _intBadgeValue

    private fun getPrefBadgeCount() {
        viewModelScope.launch {
            DataStoreInstance.getIntPreferences(
                context = getApplication(),
                key = DataStoreInstance.INT_BADGE_COUNT_KEY
            ).collect { valueInt ->
                _intBadgeValue.value = valueInt
            }
        }
    }

    fun setPrefBadgeCount(value:Int){
        viewModelScope.launch {
            DataStoreInstance.saveIntPreferences(context = getApplication(),
               key = DataStoreInstance.INT_BADGE_COUNT_KEY,
                value = value)
        }
    }

}