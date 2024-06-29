package com.islam.patient.appointment.timeSlot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.islam.domain.model.SpecialityResponse
import com.islam.domain.model.State
import com.islam.domain.model.TimeSlot
import com.islam.patient.R
import com.islam.patient.appointment.clinic.ClinicFragment
import com.islam.patient.appointment.clinic.ClinicFragmentArgs
import com.islam.patient.appointment.doctor.adapters.SpacingVerticalItemDecoration
import com.islam.patient.appointment.doctor.doctorProfile.DoctorProfileFragmentArgs
import com.islam.patient.appointment.doctor.home.HomeViewModel
import com.islam.patient.databinding.FragmentClinicBinding
import com.islam.patient.databinding.FragmentHomeBinding
import com.islam.patient.databinding.FragmentTimeSlotBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class TimeSlotFragment : Fragment() {
    private lateinit var binding: FragmentTimeSlotBinding
    private lateinit var timeSlotAdapter: TimeSlotAdapter
    private val viewModel: TimeSlotViewmodel by viewModels()
    val args: TimeSlotFragmentArgs by navArgs<TimeSlotFragmentArgs>()
    private var timeSlotresponse: List<TimeSlot>? = null
    private var extractedDayOfWeek: String? = null
    private var extractedDate: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTimeSlotBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTimeSlotRecyclerView()
        val doctor = args.doctor

        val day = args.doctorAvailablity.day
        val parts = day?.split(" ")
        if (parts?.size == 2){
             extractedDayOfWeek = parts[0]
             extractedDate = parts[1]
        }
       val finalDate = extractedDate?.let { convertDateFormat(it) }

        doctor.uid?.let { finalDate?.let { it1 -> viewModel.getTimeSlotsForDoctor(it, it1) } }
        observingTimeSlotStateFlow()

    }

//    private fun getCurrentDate(): String {
//        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//        val date = Date()
//        return dateFormat.format(date)
//    }

    private fun setUpTimeSlotRecyclerView(){
        timeSlotAdapter = TimeSlotAdapter()
        binding.recyclerView.apply {
            adapter = timeSlotAdapter
            layoutManager = GridLayoutManager(requireContext(),3)
            addItemDecoration(SpacingVerticalItemDecoration(20))
        }
    }



    private fun observingTimeSlotStateFlow(){
        lifecycleScope.launch {
            viewModel.timeSlotState.collect{
                when(it){
                    is State.Success ->{
                        hideProgressBar()
                        it.data.let{response ->
                            timeSlotresponse = response
                            timeSlotAdapter.submitList(response)
                        }
                    }
                    is State.Loading ->{
                        showProgressBar()
                    }
                    is State.Error ->{
                        hideProgressBar()
                        it.message.let {
                            Toast.makeText(
                                requireContext(),
                                "An error is $it",Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    else -> {
                        Toast.makeText(requireContext(),"any thing",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }


    private fun convertDateFormat(inputDate: String): String {
        val inputFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date)
    }


}