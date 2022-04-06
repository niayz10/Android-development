package com.example.todo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.MyApplication
import com.example.todo.R
import com.example.todo.ToDoListAdapter
import com.example.todo.Database
import com.example.todo.dao.ToDoDao
import com.example.todo.model.ToDoItem

class ToDoListFragment: Fragment() {
    lateinit var db: Database
    lateinit var  todoDao: ToDoDao
    private lateinit var items: List<ToDoItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        db = MyApplication.instance.getDatabase()!!
        todoDao = db.todoDao()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        items = todoDao.getAll()

        val layout = inflater.inflate(R.layout.todo_list, container, false)
        val list = layout.findViewById(R.id.list_view) as RecyclerView?
        val adapter = ToDoListAdapter(items, activity)
        val layoutManager = LinearLayoutManager(activity)
        list?.layoutManager = layoutManager
        list?.adapter = adapter

        return layout
    }
}