package com.example.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentEditTodoBinding
import com.example.todoapp.model.Todo
import com.example.todoapp.viewmodel.DetailTodoViewModels
import kotlinx.android.synthetic.main.fragment_create_todo.*

class EditTodoFragment : Fragment(), RadioButtonListener, TodoSaveChangesListener {
    private lateinit var viewModel: DetailTodoViewModels
    private  lateinit var dataBinding:FragmentEditTodoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentEditTodoBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailTodoViewModels::class.java)
        val uuid= EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()

        /*
        buttonAdd.setOnClickListener{
            val radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
            viewModel.update(
                uuid,
                editTitle.text.toString(),
                editNotes.text.toString(),
                radio.tag.toString().toInt()
            )
            Toast.makeText(view.context, "TODO Update", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }
        */

        //Instantiate Listener
        dataBinding.radioListener = this
        dataBinding.saveListener = this
    }

    fun observeViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner){
            dataBinding.todo = it
            /*
            editTitle.setText(it.title)
            editNotes.setText(it.note)
            when (it.priority){
                1 -> radioLow.isChecked = true;
                2 -> radioMedium.isChecked = true;
                else -> radioHigh.isChecked = true;
            }*/
        }

    }

    override fun onRadioClick(view: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }

    override fun onSaveChangeClick(view: View, obj: Todo) {
        viewModel.update(obj)
        Toast.makeText(view.context, "Todo Updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(view).popBackStack()
    }

}