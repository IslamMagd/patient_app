package com.islam.patient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.islam.domain.model.Doctor
import com.islam.patient.databinding.ListItemDoctorBinding

class DoctorAdapter: ListAdapter<Doctor,DoctorAdapter.ViewHolder>(DoctorDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorAdapter.ViewHolder {
        return ViewHolder(ListItemDoctorBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: DoctorAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    class ViewHolder(private val itemBinding: ListItemDoctorBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(doctor: Doctor){
            itemBinding.apply {
                textViewDoctorName.text = doctor.name
                textViewDoctorSpeciality.text = doctor.specialty
            }
        }
    }
    class DoctorDiffCallback: DiffUtil.ItemCallback<Doctor>() {
        override fun areItemsTheSame(oldItem: Doctor, newItem: Doctor)
        : Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Doctor, newItem: Doctor)
        : Boolean {
            return oldItem == newItem
        }

    }
}
