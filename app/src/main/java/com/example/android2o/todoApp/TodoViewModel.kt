package com.example.android2o.todoApp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android2o.database.TodoDB
import com.example.android2o.MainApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class TodoViewModel:ViewModel() {

    private val _todoList = MutableLiveData<List<Todo>>()
    private var todoMainList = mutableListOf<Todo>()

    private val todoDao = MainApplication.todoDataBase.getTodoDao()

    val todoDBList:LiveData<List<TodoDB>> = todoDao.getAllToDo()


    //simple list
     val todoList:LiveData<List<Todo>> = _todoList

    //simple get list
     private fun getAllTodoList(){
         _todoList.value = todoMainList.reversed()
    }

    //simple list add
    @RequiresApi(Build.VERSION_CODES.O)
     fun addToList(title:String){
        todoMainList.add(Todo(System.currentTimeMillis().toInt(), title, Date.from(Instant.now())))
        getAllTodoList()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addToDBList(title: String){
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.addToDo(
                TodoDB(id = System.currentTimeMillis().toInt(),
                    title,Date.from(Instant.now())))
        }
    }

    fun deleteFromDBList(id:Int,index: Int){
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteToDo(id)
        }
    }

    //simple list delete
     fun deleteFromList(id:Int,index:Int){
        todoMainList.removeAt(index)
         getAllTodoList()

    }
}