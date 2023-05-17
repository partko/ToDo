package com.example.assignment4

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment4.adapter.ToDoAdapter
import com.example.assignment4.model.ToDoModel
import com.example.assignment4.utils.DBHandler
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), DialogCloseListener {
    private val TAG = "myLogs"
    private lateinit var recyclerView: RecyclerView
    private lateinit var tasksAdapter: ToDoAdapter
    private var taskList: MutableList<ToDoModel> = ArrayList<ToDoModel>()
    //private var db: DBHandler? = null
    val config = RealmConfiguration.create(schema = setOf(Item::class))
    val realm: Realm = Realm.open(config)
    private var addButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        db = DBHandler(this)
        db!!.openDatabase()
        //Log.d(TAG, "")

        //taskList = ArrayList<ToDoModel>()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        tasksAdapter = ToDoAdapter(db!!, this@MainActivity)
        recyclerView.adapter = tasksAdapter

        addButton = findViewById<Button>(R.id.add_button)
        taskList = db!!.allTasks
        taskList.reverse()
        tasksAdapter!!.setTasks(taskList)
        addButton!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                AddTask.newInstance().show(supportFragmentManager, AddTask.TAG)
            }
        })

//        val task1 = ToDoModel()
//        task1.id = 1
//        task1.task = "task"
//        task1.status = 0
//        val task2 = ToDoModel()
//        task2.id = 2
//        task2.task = "some task"
//        task2.status = 0
//        val task3 = ToDoModel()
//        task3.id = 3
//        task3.task = "task task task"
//        task3.status = 0
//        for (i in 1..20) {
//            taskList.add(task1)
//            taskList.add(task2)
//            taskList.add(task3)
//        }
//        tasksAdapter.setTasks(taskList)

    }
    override fun handleDialogClose(dialog: DialogInterface?) {
        taskList = db!!.allTasks
        taskList.reverse()
        tasksAdapter.setTasks(taskList)
        tasksAdapter.notifyDataSetChanged()
    }
}
