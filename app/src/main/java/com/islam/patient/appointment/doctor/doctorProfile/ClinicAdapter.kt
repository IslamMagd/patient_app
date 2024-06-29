package com.islam.patient.appointment.doctor.doctorProfile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.islam.domain.model.Clinic
import com.islam.patient.appointment.doctor.adapters.OnItemClickListener
import com.islam.patient.databinding.ListItemClinicBinding

class ClinicAdapter(
    val onClinciClickListener: OnClinicClickListener? = null
): ListAdapter<Clinic, ClinicAdapter.ViewHolder>(
    ClinicDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemClinicBinding.inflate(
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
        val itemBinding: ListItemClinicBinding
    ): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(clinic: Clinic) {
            itemBinding.apply {
                textViewAddressValue.text = clinic.address
                textViewExaminationPriceValue.text = clinic.examination.toString()
                textViewFollowUpPriceValue.text = clinic.followUp.toString()
                textViewPhoneValue.text = clinic.phone
                textViewClinicName.text = clinic.name
                root.setOnClickListener{
                    onClinciClickListener?.clickOnClinic(clinic)
                }
            }
        }
    }

    class ClinicDiffCallback: DiffUtil.ItemCallback<Clinic>() {
        override fun areItemsTheSame(oldItem: Clinic, newItem: Clinic): Boolean {
           return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Clinic, newItem: Clinic): Boolean {
           return oldItem == newItem
        }

    }

}
