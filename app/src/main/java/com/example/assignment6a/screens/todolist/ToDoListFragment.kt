package com.example.assignment6a.screens.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment6a.APP
import com.example.assignment6a.MainActivity
import com.example.assignment6a.R
import com.example.assignment6a.REPOSITORY
import com.example.assignment6a.adapters.ToDoAdapter
import com.example.assignment6a.databinding.FragmentToDoListBinding
import com.example.assignment6a.models.TaskModel
import com.example.assignment6a.screens.detail.DetailViewModel

class ToDoListFragment : Fragment() {
    lateinit var binding: FragmentToDoListBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ToDoAdapter
    //val viewModel = ViewModelProvider(this).get(ToDoListViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToDoListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(ToDoListViewModel::class.java)
        viewModel.initDatabase()
        recyclerView = binding.rvTodolist
        adapter = ToDoAdapter()
        recyclerView.adapter = adapter
        viewModel.getAllTasks().observe(viewLifecycleOwner, {list ->
            adapter.setList(list.asReversed())
        })

        binding.btnAdd.setOnClickListener {
            APP.navController.navigate(R.id.action_toDoListFragment_to_addFragment)
        }
    }


    companion object {
        //val viewModel = ViewModelProvider().get(ToDoListViewModel::class.java)
        fun clickTask(taskModel: TaskModel) {
            val bundle =  Bundle()
            bundle.putSerializable("task", taskModel)
            APP.navController.navigate(R.id.action_toDoListFragment_to_detailFragment, bundle)
        }
        fun delTask(taskModel: TaskModel) {
            //viewModel.delete(taskModel) {}
            REPOSITORY.delTask(taskModel) {

            }
        }
        fun updateTask(taskModel: TaskModel) {
            val newTaskModel = TaskModel(id = taskModel.id, title = taskModel.title, status = !taskModel.status, description = taskModel.description)
            REPOSITORY.updateTask(newTaskModel) {

            }
        }
    }
}