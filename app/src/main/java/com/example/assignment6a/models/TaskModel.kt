package com.example.assignment6a.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "todo_table")
class TaskModel (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo
    var title: String = "",

    @ColumnInfo
    var status: Boolean = false,

    @ColumnInfo
    var description: String = ""
) : Serializable