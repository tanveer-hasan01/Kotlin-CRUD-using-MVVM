package com.example.projectkt.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")

data class UsersModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var firstName: String,
    var last_name:String,
    var age:Int
)