package com.example.assignment6a.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.assignment6a.db.dao.ToDoDao
import com.example.assignment6a.models.TaskModel

@Database(entities = [TaskModel::class], version = 1)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun getToDoDao(): ToDoDao

    companion object{
        private var database: ToDoDatabase ?= null

        @Synchronized
        fun getInstance(context: Context): ToDoDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(context, ToDoDatabase::class.java, "db").allowMainThreadQueries().build()
                database as ToDoDatabase
            } else {
                database as ToDoDatabase
            }
        }
    }
}