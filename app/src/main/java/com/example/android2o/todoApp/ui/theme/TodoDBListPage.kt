package com.example.android2o.todoApp.ui.theme

import com.example.android2o.todoApp.TodoViewModel

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android2o.database.TodoDB
import com.example.android2o.R
import com.example.android2o.todoApp.Todo
import com.example.android2o.uipackage.SpacerValue
import java.text.SimpleDateFormat
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoDBListPage(viewModel: TodoViewModel) {
    val context = LocalContext.current
    //simple list
    val todolist by viewModel.todoList.observeAsState()
    //db list
    val todoDBList by viewModel.todoDBList.observeAsState()


    var inputText by remember {
        mutableStateOf("")
    }
    /**
    *  todo check for long length text it hides add button check ui first then move to API
    * */
    Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically

        ) {
            OutlinedTextField(modifier = Modifier.weight(1f)
                .padding(5.dp), value = inputText, onValueChange = {
                inputText = it
            }, label = {Text(text = if ("Enter  Task".length > 11) {
                "Enter Task".take(11) + "..." // Truncate and add ellipsis
            } else {
                "Enter Task"
            })})
            Button(onClick = {

                viewModel.addToDBList(inputText)
                inputText = ""
            }) {
                Text(text =  stringResource(R.string.Add))
            }
        }
        todoDBList?.let {
            LazyColumn(content = {
                itemsIndexed(it){ index: Int,item: TodoDB ->
                    // display data in your item raw
                    TodoItem(index= index,item = item,viewModel, onDelete = {
//                        Log.e("index ","index ")
//                        viewModel.deleteFromList(id = item.id,index = index)
                        viewModel.deleteFromDBList(id = item.id,index = index)
                    })
                }
            })
        }?: NoDataFound(context)
    }
}

@Composable
fun NoDataFound(context: Context) {
    SpacerValue(7)
    Text(text = "No Data Found", modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center, fontSize = 20.sp)
}


@Composable
        /*
        * parameter name / function name: function parameter Or plain function ->
        *  return type of function
        * onDelete:() -> Unit
        *
        * //here for simple list use Todo clas from DB class use TodoDB
        * */
fun TodoItem(index: Int, item: TodoDB, viewModel: TodoViewModel, onDelete: () -> Unit) {
    Log.e("DAte_ ",item.createdAt.toString())
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(6.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(Color.Blue),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.padding(10.dp).weight(1f)) {
            Text(text = convertDate(item.createdAt),
                modifier = Modifier.padding(0.dp),
                fontSize = 12.sp,
                color = Color.LightGray
            )
            Text(text = item.title, fontSize = 16.sp,
                color = Color.White)
            Text(text = "test for checking ", fontSize = 20.sp, color = Color.Green)
        }
        IconButton(onClick = onDelete
//            Log.e("ooooo","opopoopo")
//            viewModel.deleteFromList(id = item.id,index = index)
        ) {
            Icon(painter = painterResource(R.drawable.baseline_auto_delete_24),
                contentDescription = "Delete", tint = Color.White)
        }
    }



}


fun convertDate(createdAt: Date): String {
    return SimpleDateFormat("hh:mm:aa , dd/mm/yy").format(createdAt)
}
