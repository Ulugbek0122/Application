package com.example.myapplication.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.local.room.dao.ContactDao
import com.example.myapplication.data.local.room.entity.ContactEntity

@Database(entities = [ContactEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
 abstract fun contactDao():ContactDao
}