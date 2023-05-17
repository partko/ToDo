package com.example.assignment4.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.assignment4.AddTask
import com.example.assignment4.MainActivity
import com.example.assignment4.TaskActivity
import com.example.assignment4.model.ToDoModel
import com.example.assignment4.utils.DBHandler


//import com.example.assignment4.R


class ToDoAdapter(db: DBHandler, activity: MainActivity) :
    RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {
    private var todoList: MutableList<ToDoModel>? = null
    private val db: DBHandler
    private val activity: MainActivity

    init {
        this.db = db
        this.activity = activity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(com.example.assignment4.R.layout.task_layout, parent, false)
        return ViewHolder(itemView)
    }

    class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        var task: CheckBox
        var delBtn: ImageButton

        init {
            task = view.findViewById<CheckBox>(com.example.assignment4.R.id.checkbox)
            delBtn = view.findViewById<ImageButton>(com.example.assignment4.R.id.del)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        db.openDatabase()
        val item = todoList!![position]
        holder.task.id = item.id
        holder.task.text = item.task
        holder.task.isChecked = toBoolean(item.status)
        holder.task.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                db.updateStatus(item.id, 1)
                holder.task.paintFlags = holder.task.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                db.updateStatus(item.id, 0)
                holder.task.paintFlags = holder.task.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
        holder.delBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                deleteItem(holder.adapterPosition)
            }
        })
        holder.task.setOnLongClickListener(OnLongClickListener {
            val intent = Intent(getContext(), TaskActivity::class.java)
            intent.putExtra("task", holder.task.text)
            startActivity(activity, intent, null)
//            val toast = Toast.makeText(getContext(), "Long Click", Toast.LENGTH_SHORT)
//            toast.setGravity(Gravity.CENTER, 0, 0)
//            toast.show()
            false
        })
    }

    private fun toBoolean(n: Int): Boolean {
        return n != 0
    }

    override fun getItemCount(): Int {
        return todoList!!.size
    }

    fun getContext(): Context? {
        return activity
    }

    fun setTasks(todoList: MutableList<ToDoModel>?) {
        this.todoList = todoList
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        val item = todoList!![position]
        db.deleteTask(item.id)

        todoList!!.removeAt(position)
        notifyItemRemoved(position)
    }

    fun editItem(position: Int) {
        val item = todoList!![position]
        val bundle = Bundle()
        bundle.putInt("id", item.id)
        bundle.putString("task", item.task)
        val fragment = AddTask()
        fragment.setArguments(bundle)
        fragment.show(activity.supportFragmentManager, AddTask.TAG)
    }

}