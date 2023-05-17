package com.example.assignment6a.screens.todolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment6a.REPOSITORY
import com.example.assignment6a.db.ToDoDatabase
import com.example.assignment6a.db.repository.ToDoRealization
import com.example.assignment6a.models.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoListViewModel(application: Application): AndroidViewModel(application) {
    val context = application

    fun initDatabase() {
        val toDoDao = ToDoDatabase.getInstance(context).getToDoDao()
        REPOSITORY = ToDoRealization(toDoDao)
    }
    fun getAllTasks(): LiveData<List<TaskModel>> {
        return REPOSITORY.allTasks
    }

    fun delete(taskModel: TaskModel, onSuccess:() -> Unit) =
        viewModelScope.launch (Dispatchers.IO) {
            REPOSITORY.deleteTask(taskModel) {
                onSuccess()
            }
        }

    fun del(taskModel: TaskModel, onSuccess:() -> Unit) {
        REPOSITORY.delTask(taskModel) {
            onSuccess()
        }
//        viewModelScope.launch (Dispatchers.IO) {
//            REPOSITORY.deleteTask(taskModel) {
//                onSuccess()
//            }
//        }
    }

//    fun updateActionCheckStatus(taskModel: TaskModel, onSuccess:() -> Unit) {
//        val newTaskModel = taskModel.copy(checkStatus = !taskModel.status)
//        // action.checkStatus = !action.checkStatus // areContentsTheSame returns true in this case, so don't use it
//        viewModelScope.launch (Dispatchers.IO) {
//            REPOSITORY.updateTask(newTaskModel) {
//                onSuccess()
//            }
//        }
//    }

}