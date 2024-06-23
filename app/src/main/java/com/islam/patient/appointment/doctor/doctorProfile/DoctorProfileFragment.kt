package com.islam.patient.appointment.doctor.doctorProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.islam.domain.model.Clinic
import com.islam.domain.model.State
import com.islam.patient.appointment.doctor.adapters.SpacingVerticalItemDecoration
import com.islam.patient.appointment.doctor.home.HomeFragmentDirections
import com.islam.patient.databinding.FragmentDoctorProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DoctorProfileFragment : Fragment(),OnClinicClickListener {
    private lateinit var binding: FragmentDoctorProfileBinding
    private lateinit var clinicAdapter: ClinicAdapter
    private val viewModel: DoctorProfileViewModel by viewModels()
    val args: DoctorProfileFragmentArgs by navArgs<DoctorProfileFragmentArgs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDoctorProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClinicRecyclerView()
        val doctor = args.doctor
        binding.textViewDoctorName.text = doctor.name
        clinicAdapter.submitList(doctor.clinics)
    }

    private fun setUpClinicRecyclerView() {
        clinicAdapter = ClinicAdapter(this)
        binding.recyclerViewDoctor.apply {
            adapter = clinicAdapter
            addItemDecoration(SpacingVerticalItemDecoration(20))
        }
    }
    override fun clickOnClinic(clinic: Clinic) {
        val action: NavDirections? = clinic?.let {
            DoctorProfileFragmentDirections.actionDoctorProfileFragmentToClinicFragment(it)
        }
        if (action != null) {
            findNavController().navigate(action)
        }

    }

}