package com.example.assignment6a.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.assignment6a.models.TaskModel

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(taskModel: TaskModel)

    @Delete
    suspend fun delete(taskModel: TaskModel)

    @Delete
    fun del(taskModel: TaskModel)

    @Update
    fun update(action: TaskModel)

    @Query("SELECT * from todo_table")
    fun getAll(): LiveData<List<TaskModel>>
}