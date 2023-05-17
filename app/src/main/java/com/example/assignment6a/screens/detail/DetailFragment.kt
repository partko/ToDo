package com.example.assignment6a.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.assignment6a.APP
import com.example.assignment6a.R
import com.example.assignment6a.databinding.FragmentDetailBinding
import com.example.assignment6a.models.TaskModel


class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    lateinit var currentTask: TaskModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        currentTask = arguments?.getSerializable("task") as TaskModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding.detailTitle.text = currentTask.title
        binding.detailInfo.text = currentTask.description

        binding.btnDelete.setOnClickListener {
            viewModel.delete(currentTask) {}
            APP.navController.navigate(R.id.action_detailFragment_to_toDoListFragment)
        }
        binding.btnBack.setOnClickListener {
            APP.navController.navigate(R.id.action_detailFragment_to_toDoListFragment)
        }
    }
}