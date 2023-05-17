package com.example.assignment6a.db.repository

import androidx.lifecycle.LiveData
import com.example.assignment6a.models.TaskModel

interface ToDoRepository {
    val allTasks: LiveData<List<TaskModel>>
    suspend fun insertTask(taskModel: TaskModel, onSuccess:() -> Unit)
    suspend fun deleteTask(taskModel: TaskModel, onSuccess:() -> Unit)
    fun updateTask(taskModel: TaskModel, onSuccess:() -> Unit)
    fun delTask(taskModel: TaskModel, onSuccess:() -> Unit)
}