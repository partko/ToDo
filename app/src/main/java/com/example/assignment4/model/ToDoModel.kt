package com.example.assignment4.model

class ToDoModel {
    var id: Int = 0
        set(value){
            field = value
        }
        get(){
            return field
        }
    var status: Int = 0
        set(value){
            field = value
        }
        get(){
            return field
        }
    var task: String = ""
        set(value){
            field = value
        }
        get(){
            return field
        }

}