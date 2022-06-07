package com.example.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.LayoutTodoItemBinding
import com.example.todoapp.model.Todo
import kotlinx.android.synthetic.main.layout_todo_item.view.*

class TodoListAdapter(val todoList: ArrayList<Todo>, val adapterOnClick: (Todo) -> Unit) : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(), TodoCheckedChangeListener, TodoEditClickListener{
    class TodoViewHolder(var view: LayoutTodoItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = LayoutTodoItemBinding.inflate(inflater, parent, false)

        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoList[position]
        holder.view.todo = todoList[position]
        holder.view.checkboxListener = this
        holder.view.editListener = this
        /*
        val item = todoList[position]
        with(holder.view){

            val priority = when(todo.priority){
                1->"Low"
                2->"Medium"
                else->"High"
            }
            checkTask.text = "[$priority] ${todoList[position].title}"
            checkTask.setOnCheckedChangeListener { compoundButton, b ->
                if(b) adapterOnClick(todo)
            }

            buttonEdit.setOnClickListener{
                val action = TodoListFragmentDirections.actionEditTodo(todo.uuid)
                Navigation.findNavController(it).navigate(action)
            }
        }*/

    }

    override fun getItemCount() = todoList.size

    fun updateTodoList(newTodoList: List<Todo>){
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCheckChanged(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(isChecked) adapterOnClick(obj)
    }

    override fun onTodoEditClick(view: View) {
        val uuid = view.tag.toString().toInt()
        val action = TodoListFragmentDirections.actionEditTodo(uuid)
        Navigation.findNavController(view).navigate(action)
    }
}