package com.example.todo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.fragment.ToDoItemFragment
import com.example.todo.dao.ToDoDao
import com.example.todo.model.ToDoItem

class ToDoListAdapter(var items: List<ToDoItem>, var context: Context?): RecyclerView.Adapter<ToDoListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.todo_item_short, parent, false)

        return ViewHolder(items, view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: ToDoItem = items[position]
        holder.title.text = item.title
        holder.category.text = item.category
        holder.status.isChecked = item.status
        holder.id = item.id!!
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(var items: List<ToDoItem>, view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        var id: Int = -1
        var content: ConstraintLayout = view.findViewById(R.id.content)
        var title: TextView = view.findViewById(R.id.header)
        var category: TextView = view.findViewById(R.id.category)
        var status: CheckBox = view.findViewById(R.id.status)

        lateinit var db: Database
        lateinit var  todoDao: ToDoDao

        init {
            content.setOnClickListener(this)

            status.setOnClickListener {
                val checked = items.find { i -> i.id == this.id }
                checked?.status = status.isChecked

                db = MyApplication.instance.getDatabase()!!
                todoDao = db.todoDao()
                if (checked != null) {
                    todoDao.update(checked)
                }
            }
        }

        override fun onClick(v: View?) {
            val activity = v?.context
            val item = items.find { i -> i.id == this.id }
            (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main, ToDoItemFragment(item))
                .addToBackStack("secondary")
                .commit()
        }
    }
}