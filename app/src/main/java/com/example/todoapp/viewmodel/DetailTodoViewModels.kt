package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.todoapp.model.Todo
import com.example.todoapp.model.TodoDatabase
import com.example.todoapp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModels(application: Application):AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val todoLD = MutableLiveData<Todo>()

    fun addTodo(todoList: List<Todo>){
        launch {
            val db = buildDb(getApplication())
            db.todoDatabase().insertAll(*todoList.toTypedArray())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun fetch(uuid: Int){
        launch {
            val db = buildDb(getApplication())
            todoLD.value = db.todoDatabase().selectTodo(uuid)
        }
    }

    fun update(id: Int, title: String, notes: String, priority: Int){
        launch {
            val db = buildDb(getApplication())
            db.todoDatabase().update(id, title, notes, priority)
        }
    }
}