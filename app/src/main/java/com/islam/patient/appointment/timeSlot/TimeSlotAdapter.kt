package com.islam.patient.appointment.timeSlot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.islam.domain.model.TimeSlot
import com.islam.patient.databinding.ListItemTimeSlotBinding

class TimeSlotAdapter: ListAdapter<TimeSlot,TimeSlotAdapter.ViewHolder>(TimeSlotDiffCallback()){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ListItemTimeSlotBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val listItemTimeSlotBinding: ListItemTimeSlotBinding
    ): RecyclerView.ViewHolder(listItemTimeSlotBinding.root) {
        fun bind(timeSlot: TimeSlot){
            listItemTimeSlotBinding.buttonTimeSlot.text = timeSlot.startTime
        }

    }

    class TimeSlotDiffCallback: DiffUtil.ItemCallback<TimeSlot>() {
        override fun areItemsTheSame(oldItem: TimeSlot, newItem: TimeSlot): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TimeSlot, newItem: TimeSlot): Boolean {
            return oldItem == newItem
        }
    }



}
