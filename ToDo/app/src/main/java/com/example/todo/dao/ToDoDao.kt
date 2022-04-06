package com.example.todo.dao

import androidx.room.*
import com.example.todo.model.ToDoItem

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todo")
    fun getAll(): List<ToDoItem>

    @Query("SELECT * FROM todo WHERE id IN (:todoIds)")
    fun loadAllByIds(todoIds: IntArray): List<ToDoItem>

    @Insert
    fun insert(vararg todoItems: ToDoItem)

    @Delete
    fun delete(todoItem: ToDoItem)

    @Update
    fun update(todoItem: ToDoItem)
}