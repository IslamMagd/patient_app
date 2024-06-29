package com.islam.patient.appointment.doctor.doctorProfile

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
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
import com.islam.domain.model.Doctor
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
    val args: DoctorProfileFragmentArgs by navArgs<DoctorProfileFragmentArgs>()
    var doctor: Doctor? = null
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
         doctor = args.doctor
        Log.d("doctorId","print${doctor?.uid}")
        binding.textViewDoctorName.text = doctor?.name
//        binding.textViewDoctorProfile.text = doctor?.profTitle
        binding.ratingBar.apply {
            rating = 1f
            stepSize = 1f
        }
        binding.ratingBar.rating
        Toast.makeText(requireContext(),"${binding.ratingBar.rating}",Toast.LENGTH_LONG).show()
        clinicAdapter.submitList(doctor?.clinics)
    }

    private fun setUpClinicRecyclerView() {
        clinicAdapter = ClinicAdapter(this)
        binding.recyclerViewDoctor.apply {
            adapter = clinicAdapter
            addItemDecoration(SpacingVerticalItemDecoration(20))
        }
    }
    override fun clickOnClinic(clinic: Clinic) {
        val action: NavDirections? = clinic?.let {clinicValue ->
            doctor?.let { doctorValue ->
                DoctorProfileFragmentDirections.actionDoctorProfileFragmentToClinicFragment(
                    clinicValue,
                    doctorValue
                )
            }
        }
        if (action != null) {
            findNavController().navigate(action)
        }
    }

}