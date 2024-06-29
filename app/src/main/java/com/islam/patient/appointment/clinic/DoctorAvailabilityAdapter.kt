package com.islam.patient.appointment.clinic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.islam.domain.model.DoctorAvailability
import com.islam.patient.appointment.doctor.adapters.OnItemClickListener
import com.islam.patient.databinding.ListItemBookingOptionBinding

class DoctorAvailabilityAdapter(val onBookClickListener: OnBookClickListener? = null): ListAdapter<DoctorAvailability, DoctorAvailabilityAdapter.ViewHolder>(
    BookingOptionDiffCallback()
){
    inner class ViewHolder(
        private val itemBinding: ListItemBookingOptionBinding
    ): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(doctorAvailability: DoctorAvailability){
            if(doctorAvailability.available ==true) {
                itemBinding.textViewDay.text = doctorAvailability.day
                itemBinding.textViewTimeFrom.text = doctorAvailability.startTime
                itemBinding.textViewTimeTo.text = doctorAvailability.endTime
            }
            else{
                itemBinding.textViewDay.text = doctorAvailability.day
                itemBinding.textViewTimeFrom.visibility = View.GONE
                itemBinding.textViewTimeTo.visibility = View.GONE
                itemBinding.textViewTo.text = "No Available Slots"
                itemBinding.textViewBook.isEnabled = false
            }
            itemBinding.textViewBook.setOnClickListener {
                onBookClickListener?.onBookClick(doctorAvailability)
            }
        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ListItemBookingOptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class BookingOptionDiffCallback: DiffUtil.ItemCallback<DoctorAvailability>(){
        override fun areItemsTheSame(
            oldItem: DoctorAvailability,
            newItem: DoctorAvailability
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DoctorAvailability,
            newItem: DoctorAvailability
        ): Boolean {
            return oldItem == newItem
        }

    }

}