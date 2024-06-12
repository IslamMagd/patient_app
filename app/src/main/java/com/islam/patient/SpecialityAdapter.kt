package com.islam.patient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.islam.domain.model.Speciality
import com.islam.patient.databinding.ListItemSpecializationBinding

class SpecialityAdapter: ListAdapter<Speciality,SpecialityAdapter.ViewHolder>(
    SpecialityDiffCallback()
){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpecialityAdapter.ViewHolder {
        return ViewHolder(ListItemSpecializationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
            )
        )
    }

    override fun onBindViewHolder(holder: SpecialityAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val itemBinding: ListItemSpecializationBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(speciality: Speciality){
            itemBinding.textViewSpeciality.text = speciality.name
        }


    }

    class SpecialityDiffCallback: DiffUtil.ItemCallback<Speciality>(){
        override fun areItemsTheSame(oldItem: Speciality,
                                     newItem: Speciality
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Speciality,
                                        newItem: Speciality
        ): Boolean {
            return oldItem == newItem
        }

    }

}