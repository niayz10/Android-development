package com.example.todo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todo")
data class ToDoItem(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "title", index = true) var title: String? = null,
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "status") var status: Boolean = false,
    @ColumnInfo(name = "category") var category: String? = null,
    @ColumnInfo(name = "duration") var duration: String? = null)
{ }