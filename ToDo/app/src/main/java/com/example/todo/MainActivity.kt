package com.example.todo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.dao.ToDoDao
import com.example.todo.fragment.ToDoAddFragment
import com.example.todo.fragment.ToDoListFragment
import com.example.todo.model.*
import kotlinx.android.synthetic.main.todo_add_item.*
import java.sql.Timestamp
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var db: Database
    lateinit var  todoDao: ToDoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = MyApplication.instance.getDatabase()!!
        todoDao = db.todoDao()

        if(todoDao.getAll().isEmpty()) {
            addItems()
            Log.e("INITIAL", "Ok: Added")
        }

        val fragment = ToDoListFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.main, fragment)
            .commit()
    }

    override fun onClick(v: View?) {
        supportFragmentManager.popBackStack()
    }

    fun addItem(view: View) {
        this.supportFragmentManager.beginTransaction()
            .replace(R.id.main, ToDoAddFragment())
            .addToBackStack("secondary")
            .commit()
    }

    fun add(view: View) {
        val title = add_title.text.toString()
        val category = add_category.text.toString()
        val description = add_description.text.toString()
        val duration = Timestamp(System.currentTimeMillis()).toString()

        val item = ToDoItem()
        item.title = title
        item.category = category
        item.description = description
        item.duration = duration
        todoDao.insert(item)
        supportFragmentManager.popBackStack()
    }

    private fun rand(start: Int = 1, end: Int = 5): Int {
        require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
        return Random(System.nanoTime()).nextInt(end - start + 1) + start
    }

    private fun addItems() {
        for(i: Int in 1..10) {
            val c = rand()
            val title = "Title$i"
            val category = "Category$c"
            val description = "\n" +
                    "██████╗░░█████╗░██████╗░██╗░░░██╗\n" +
                    "██╔══██╗██╔══██╗██╔══██╗╚██╗░██╔╝\n" +
                    "██████╦╝██║░░██║██║░░██║░╚████╔╝░\n" +
                    "██╔══██╗██║░░██║██║░░██║░░╚██╔╝░░\n" +
                    "██████╦╝╚█████╔╝██████╔╝░░░██║░░░\n" +
                    "╚═════╝░░╚════╝░╚═════╝░░░░╚═╝░░░"
            val duration = Timestamp(System.currentTimeMillis()).toString()

            val item = ToDoItem()
            item.title = title
            item.category = category
            item.description = description
            item.duration = duration
            todoDao.insert(item)
        }
    }
}