package com.example.todoapp.view

import android.view.View
import android.widget.CompoundButton
import com.example.todoapp.model.Todo

interface TodoCheckedChangeListener{
    fun onCheckChanged(cb: CompoundButton, isChecked: Boolean, obj:Todo)
}

interface  TodoEditClickListener{
    fun onTodoEditClick(view: View)
}

interface RadioButtonListener{
    fun onRadioClick(view: View, priority:Int, obj: Todo)
}

interface TodoSaveChangesListener{
    fun onSaveChangeClick(view: View, obj: Todo)
}