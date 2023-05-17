package com.example.assignment6a.db.repository

import androidx.lifecycle.LiveData
import com.example.assignment6a.db.dao.ToDoDao
import com.example.assignment6a.models.TaskModel

class ToDoRealization(private val toDoDao: ToDoDao): ToDoRepository {
    override val allTasks: LiveData<List<TaskModel>>
        get() = toDoDao.getAll()

    override suspend fun insertTask(taskModel: TaskModel, onSuccess: () -> Unit) {
        toDoDao.insert(taskModel)
        onSuccess()
    }

    override suspend fun deleteTask(taskModel: TaskModel, onSuccess: () -> Unit) {
        toDoDao.delete(taskModel)
        onSuccess()
    }

    override fun updateTask(taskModel: TaskModel, onSuccess: () -> Unit) {
        toDoDao.update(taskModel)
        onSuccess()
    }

    override fun delTask(taskModel: TaskModel, onSuccess: () -> Unit) {
        toDoDao.del(taskModel)
        onSuccess()
    }
}