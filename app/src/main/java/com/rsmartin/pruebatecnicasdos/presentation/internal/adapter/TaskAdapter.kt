package com.rsmartin.pruebatecnicasdos.presentation.internal.adapter

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.rsmartin.pruebatecnicasdos.R
import com.rsmartin.pruebatecnicasdos.data.model.TaskEntity
import com.rsmartin.pruebatecnicasdos.data.repository.TaskRepository
import com.rsmartin.pruebatecnicasdos.data.repository.WebServiceRepository
import kotlinx.android.synthetic.main.item_tecni_layout.view.*

class TaskAdapter(val itemList: List<TaskEntity>, application: Application) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    val taskRepository: TaskRepository = TaskRepository(application)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layoutInflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tecni_layout, parent, false)

        return TaskViewHolder(layoutInflate)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = itemList[position]
        holder.bindPartido(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindPartido(item: TaskEntity) {
            itemView.typeTask.text = item.typeTask
            itemView.hourTask.text = item.hoursTask
            itemView.taskDescription.text = item.description
            itemView.checkbox.isChecked = item.taskFinished

            itemView.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                item.taskFinished = isChecked
                taskRepository.updateTask(item)
            }

        }
    }



}