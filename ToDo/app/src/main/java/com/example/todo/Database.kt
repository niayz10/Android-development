package com.example.todo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.dao.ToDoDao
import com.example.todo.model.ToDoItem

@Database(entities = [ToDoItem::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun todoDao(): ToDoDao
}