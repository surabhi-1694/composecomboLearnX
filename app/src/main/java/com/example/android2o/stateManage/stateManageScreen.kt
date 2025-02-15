package com.example.android2o.stateManage

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.android2o.uipackage.SpacerValue

/**
 * different types of states to remember the value entered by user
 * composable called each and every time anything changes in ui
 * - use: value enter in edittext ; to display in any composable view need to make variable that is remember
 * EX: var edtFirstName by remember {
 *        mutablestateof("value")
 * }
 *
 *  Different types:
 *   remember (does not survive on config change)
 *   rememberSaveable (survive on config change)
 *   ViewModel (survive on config change)
 *   LiveData  (survive on config change)
 *   MutableLiveData
 *   observeAsState
* */

@Composable
fun StateManageScreen(viewModel: StateManageViewModel){
    Log.e(TAG,"StateManageScreen")
    var edtName by remember {
        mutableStateOf("Hint")
    }

    val firstname by viewModel.firstName.observeAsState(initial = "su 6e aa badhu")

    var edtSaveable by rememberSaveable {
        mutableStateOf("hint Savable")
    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        MyText(edtName)
        MyViewmodelText(firstname, callback = { it ->
            viewModel.onFirstNameUpdate(it)
        })
        SpacerValue(6)
        MyTextField(edtName = edtName,
            edtConfigname = edtSaveable,
            onNameChange = {
            edtName = it
        }, onNameConfigSurviveChange = {
            edtSaveable = it
        })
    }
}

@Composable
fun MyViewmodelText(firstname: String,callback:(String)->Unit) {
    Column {
        OutlinedTextField(value = firstname, onValueChange = {
            viewmodelName->
            callback(viewmodelName)
        })
    }
}

@Composable
fun MyText(edtName: String) {
    Log.e(TAG,"MyText")
    Text(text = "My sample text ${edtName}", style = TextStyle(fontSize = 40.sp))
}

@Composable
fun MyTextField(edtName: String,edtConfigname:String,
                onNameChange:(String)-> Unit,
                onNameConfigSurviveChange:(String)->Unit) {
    Log.e(TAG,"MyTextField")
    Column {
        OutlinedTextField(value = edtConfigname, onValueChange = { configSurviveName ->
            onNameConfigSurviveChange(configSurviveName)
        }, label = {
            Text(text = "remembersaveble")
        })
        OutlinedTextField(
            value = edtName,
            onValueChange = { viewValue ->
                onNameChange(viewValue)
            },
            label = { Text(text = "Enter name") }
        )
    }
}