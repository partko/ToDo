package com.example.assignment6a.screens.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.assignment6a.APP
import com.example.assignment6a.R
import com.example.assignment6a.databinding.FragmentAddBinding
import com.example.assignment6a.databinding.FragmentToDoListBinding
import com.example.assignment6a.models.TaskModel

class AddFragment : Fragment() {

    lateinit var binding: FragmentAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(AddViewModel::class.java)
        binding.btnAdd.setOnClickListener {
            val title = binding.addTitle.text.toString()
            val description = binding.addInfo.text.toString()
            viewModel.insert(TaskModel(title = title, description = description)) {}
            APP.navController.navigate(R.id.action_addFragment_to_toDoListFragment)
        }
        binding.btnBack.setOnClickListener {
            APP.navController.navigate(R.id.action_addFragment_to_toDoListFragment)
        }
    }
}