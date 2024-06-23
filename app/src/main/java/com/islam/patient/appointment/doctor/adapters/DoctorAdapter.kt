package com.islam.patient.appointment.doctor.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.islam.domain.model.Doctor
import com.islam.patient.databinding.ListItemDoctorBinding

class DoctorAdapter(
    val onItemClickListener: OnItemClickListener? = null
): ListAdapter<Doctor, DoctorAdapter.ViewHolder>(DoctorDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ListItemDoctorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    inner class ViewHolder(
        private val itemBinding: ListItemDoctorBinding
    ): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(doctor: Doctor){
            itemBinding.apply {
                textViewDoctorName.text = doctor.name
                textViewDoctorSpeciality.text = doctor.specialty
                buttonMakeAppointment.setOnClickListener{
                    Log.d("doctorAdapter","click$doctor")
                    onItemClickListener?.onItemClick(doctor)
                }
            }
        }
    }
    class DoctorDiffCallback: DiffUtil.ItemCallback<Doctor>() {
        override fun areItemsTheSame(oldItem: Doctor, newItem: Doctor)
        : Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: Doctor, newItem: Doctor)
        : Boolean {
            return oldItem == newItem
        }

    }
}
