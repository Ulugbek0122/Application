package com.example.myapplication.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class ContactEntity(
    var id:Int,
    var name:String,
    var phone:String,
    @PrimaryKey(autoGenerate = true)
    var localId:Int = 0
)
