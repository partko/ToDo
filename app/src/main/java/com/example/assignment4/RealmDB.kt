package com.example.assignment4

import com.example.assignment4.model.ToDoModel

class Item() : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.create()
//    var taskId: Int = 0
//    var status: Boolean = false
//    var text: String = ""
    val taskList: MutableList<ToDoModel> = ArrayList()
//    constructor(ownerId: String = "") : this() {
//        owner_id = ownerId
//    }
}