package com.example.assignment6a.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment6a.REPOSITORY
import com.example.assignment6a.models.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {
    fun delete(taskModel: TaskModel, onSuccess:() -> Unit) =
        viewModelScope.launch (Dispatchers.IO) {
            REPOSITORY.deleteTask(taskModel) {
                onSuccess()
            }
        }
}