package com.islam.patient.appointment.clinic

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.islam.domain.model.DoctorAvailability
import com.islam.patient.R
import com.islam.patient.appointment.doctor.adapters.SpacingHorizontaltemDecoration
import com.islam.patient.appointment.doctor.adapters.SpacingVerticalItemDecoration
import com.islam.patient.appointment.doctor.doctorProfile.ClinicAdapter
import com.islam.patient.databinding.FragmentClinicBinding
import com.islam.patient.databinding.FragmentDoctorProfileBinding
import com.islam.patient.util.adjustDaysToStartFromToday
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class ClinicFragment : Fragment() {
    private lateinit var binding: FragmentClinicBinding
    val args: ClinicFragmentArgs by navArgs<ClinicFragmentArgs>()
    private lateinit var doctorAvailablitiyAdapter: DoctorAvailabilityAdapter
    lateinit var bookingOptions: List<DoctorAvailability>
    private var currentIndex = 0
    private val itemsPerPage = 2
//    private val bookingOptions = listOf(
//        DoctorAvailability(1, "Today", "4:00 PM","7:00 PM"),
//        DoctorAvailability(2, "Today", "4:00 PM","7:00 PM"),
//        DoctorAvailability(3, "Today", "4:00 PM","7:00 PM"),
//        DoctorAvailability(4, "Today", "2:30 PM","7:00 PM"),
//        DoctorAvailability(5, "Today", "4:00 PM","7:00 PM"),
//        DoctorAvailability(6, "Today", "4:00 PM","7:00 PM"),
//        DoctorAvailability(7, "Today", "4:00 PM","7:00 PM"),
//        DoctorAvailability(8, "Today", "4:00 PM","7:00 PM"),
//        DoctorAvailability(9, "Today", "4:00 PM","7:00 PM"),
//        DoctorAvailability(10, "Today", "4:00 PM","7:00 PM"),
//        DoctorAvailability(11, "Today", "2:00 PM","7:00 PM")
//    )

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
        bookingOptions = adjustDaysToStartFromToday(doctorAvailabilities)

        updateDisplayedItems()
        clickOnLeftArrow()
        clickOnRightArrow()
    }

    private fun setUpDoctorAvailablityRecyclerView() {
        doctorAvailablitiyAdapter = DoctorAvailabilityAdapter()
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
}