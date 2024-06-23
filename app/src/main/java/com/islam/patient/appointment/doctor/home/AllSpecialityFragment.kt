package com.islam.patient.appointment.doctor.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.islam.patient.appointment.doctor.adapters.SpacingVerticalItemDecoration
import com.islam.patient.appointment.doctor.adapters.SpecialityAdapter
import com.islam.patient.databinding.FragmentAllSpecialityBinding

class AllSpecialityFragment : Fragment() {
    private lateinit var binding: FragmentAllSpecialityBinding
    private lateinit var specialityAdapter: SpecialityAdapter
    private val args: AllSpecialityFragmentArgs by navArgs<AllSpecialityFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllSpecialityBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSpecialitiesRecyclerView()
        val specialityResponse = args.specialityResponse
        specialityAdapter.submitList(specialityResponse)
    }

    private fun setUpSpecialitiesRecyclerView(){
        specialityAdapter = SpecialityAdapter()
        binding.recyclerViewSpeciality.apply {
            adapter = specialityAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            addItemDecoration(SpacingVerticalItemDecoration(20))
        }
    }

}

