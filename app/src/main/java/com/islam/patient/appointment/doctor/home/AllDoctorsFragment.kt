package com.islam.patient.appointment.doctor.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.islam.patient.appointment.doctor.adapters.DoctorAdapter
import com.islam.patient.appointment.doctor.adapters.SpacingVerticalItemDecoration
import com.islam.patient.databinding.FragmentAllDoctorsBinding

class AllDoctorsFragment : Fragment() {
    private lateinit var binding: FragmentAllDoctorsBinding
    private lateinit var doctorAdapter: DoctorAdapter
    private val args: AllDoctorsFragmentArgs by navArgs<AllDoctorsFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllDoctorsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpDoctorsRecyclerView()
        val doctorResponse = args.doctorResponse
        doctorAdapter.submitList(doctorResponse)
    }

    private fun setUpDoctorsRecyclerView() {
        doctorAdapter = DoctorAdapter()
        binding.recyclerViewDoctor.apply {
            adapter = doctorAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            addItemDecoration(SpacingVerticalItemDecoration(20))
        }
    }
}