package com.example.assignment4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        val actionBar = supportActionBar
        actionBar!!.title = "Task"
        actionBar.setDisplayHomeAsUpEnabled(true)
        textView = findViewById(R.id.task)
        textView.text = intent.getStringExtra("task").toString()

    }
}