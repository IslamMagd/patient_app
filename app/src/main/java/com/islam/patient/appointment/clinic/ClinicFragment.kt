package com.islam.patient.appointment.clinic

import android.content.Context
import android.content.res.Configuration
import android.media.VolumeShaper
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.islam.domain.model.Doctor
import com.islam.domain.model.DoctorAvailability
import com.islam.patient.R
import com.islam.patient.appointment.doctor.adapters.SpacingHorizontaltemDecoration
import com.islam.patient.appointment.doctor.adapters.SpacingVerticalItemDecoration
import com.islam.patient.appointment.doctor.doctorProfile.ClinicAdapter
import com.islam.patient.appointment.doctor.home.HomeFragmentDirections
import com.islam.patient.databinding.FragmentClinicBinding
import com.islam.patient.databinding.FragmentDoctorProfileBinding
import com.islam.patient.util.adjustDaysToStartFromToday
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class ClinicFragment : Fragment(),OnBookClickListener {
    private lateinit var binding: FragmentClinicBinding
    val args: ClinicFragmentArgs by navArgs<ClinicFragmentArgs>()
    private lateinit var doctorAvailablitiyAdapter: DoctorAvailabilityAdapter
    lateinit var bookingOptions: List<DoctorAvailability>
    private var doctor: Doctor? = null
    private var currentIndex = 0
    private val itemsPerPage = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentClinicBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpDoctorAvailablityRecyclerView()

         val doctorAvailabilities = args.clinic.doctorAvailabilities as List<DoctorAvailability>
         doctor = args.doctor
        bookingOptions = adjustDaysToStartFromToday(doctorAvailabilities)
        getLocationOfClinic()

        updateDisplayedItems()
        clickOnLeftArrow()
        clickOnRightArrow()
    }

    override fun onResume() {
        super.onResume()
        binding.map.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.map.onPause()
    }

    private fun setUpDoctorAvailablityRecyclerView() {
        doctorAvailablitiyAdapter = DoctorAvailabilityAdapter(this)
        binding.recyclerView.apply {
            adapter = doctorAvailablitiyAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            addItemDecoration(SpacingHorizontaltemDecoration(20))
        }
    }

    private fun updateDisplayedItems() {
        val endIndex = (currentIndex + itemsPerPage).coerceAtMost(bookingOptions.size)
        val displayedItems = bookingOptions.subList(currentIndex, endIndex)
        doctorAvailablitiyAdapter.submitList(displayedItems )
    }

    private fun clickOnLeftArrow(){
        binding.imageButtonLeftArrow.setOnClickListener {
            currentIndex -= itemsPerPage
            if (currentIndex < 0) currentIndex = 0
            updateDisplayedItems()
        }
    }

    private fun clickOnRightArrow(){
        binding.imageButtonRightArrow.setOnClickListener {
            currentIndex += itemsPerPage
            if (currentIndex >= bookingOptions.size) {
                currentIndex = bookingOptions.size - itemsPerPage
                if (currentIndex < 0) currentIndex = 0
            }
            updateDisplayedItems()
        }
    }

    override fun onBookClick(doctorAvailability: DoctorAvailability) {
        val action: NavDirections? = doctorAvailability?.let {doctorAvailabilityValue ->
            doctor?.let { doctor ->
                ClinicFragmentDirections.actionClinicFragmentToTimeSlotFragment(
                    doctorAvailabilityValue,
                    doctor
                )
            }

        }
        if (action != null) {
            findNavController().navigate(action)
        }
    }

    private fun  getLocationOfClinic(){
        binding.map.setMultiTouchControls(true)
        val ctx = requireContext()
        org.osmdroid.config.Configuration.getInstance().load(
            ctx,
            ctx?.getSharedPreferences("osmdroid", Context.MODE_PRIVATE)
        )
        val startPoint = GeoPoint(args.clinic.latitude, args.clinic.longitude)
        binding.map.controller.setZoom(15.0)
        binding.map.controller.setCenter(startPoint)
        val marker = Marker(binding.map)
        marker.position = startPoint
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.title = "Clinic"
        binding.map.overlays.add(marker)
    }

}