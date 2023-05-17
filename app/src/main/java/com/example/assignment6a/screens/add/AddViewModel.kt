package com.example.assignment6a.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment6a.REPOSITORY
import com.example.assignment6a.models.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel: ViewModel() {
    fun insert(taskModel: TaskModel, onSuccess:() -> Unit) =
        viewModelScope.launch (Dispatchers.IO) {
            REPOSITORY.insertTask(taskModel) {
                onSuccess()
            }
        }
}