package com.islam.patient.appointment.doctor.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.islam.domain.model.Doctor
import com.islam.domain.model.DoctorResponse
import com.islam.domain.model.SpecialityResponse
import com.islam.domain.model.State
import com.islam.patient.R
import com.islam.patient.appointment.doctor.adapters.SpacingVerticalItemDecoration
import com.islam.patient.appointment.doctor.adapters.SpacingHorizontaltemDecoration
import com.islam.patient.appointment.doctor.adapters.DoctorAdapter
import com.islam.patient.appointment.doctor.adapters.OnItemClickListener
import com.islam.patient.appointment.doctor.adapters.SpecialityAdapter
import com.islam.patient.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(),OnItemClickListener {
    lateinit var binding: FragmentHomeBinding
    lateinit var doctorAdapter: DoctorAdapter
    lateinit var specialityAdapter: SpecialityAdapter
    private val viewModel: HomeViewModel by viewModels()
    private var doctorResponse: DoctorResponse? = null
    private var response: SpecialityResponse? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickOnSearchEditText()
        setUpDoctorsRecyclerView()
        viewModel.getAllDoctors()
        observingDoctorsStateFlow()

        clickOnSeeAllForDoctors()
        clickOnSeeAllForSpeciality()

        setUpSpecialitiesRecyclerView()
        viewModel.getAllSpecialities()
        observingSpecialtiesStateFlow()

    }

    private fun clickOnSearchEditText(){
        binding.editTextSearch.setOnClickListener{
            findNavController().navigate(R.id.action_firstFragment_to_searchDoctorFragment)
        }
    }

    private fun setUpSpecialitiesRecyclerView(){
        specialityAdapter = SpecialityAdapter()
        binding.recyclerViewSpeciality.apply {
            adapter = specialityAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            addItemDecoration(SpacingHorizontaltemDecoration(20))
        }
    }

    private fun setUpDoctorsRecyclerView() {
        doctorAdapter = DoctorAdapter(this)
        binding.recyclerViewDoctor.apply {
            adapter = doctorAdapter
            addItemDecoration(SpacingVerticalItemDecoration(20))
        }
    }

    private fun observingDoctorsStateFlow(){
        lifecycleScope.launch {
            viewModel.doctorsStateFlow.collect{
                when(it){
                    is State.Success ->{
                        hideProgressBar()
                        it.data.let{response ->
                            doctorResponse = response
                            doctorAdapter.submitList(response)
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


    private fun observingSpecialtiesStateFlow(){
        lifecycleScope.launch {
            viewModel.specialitiesStateFlow.collect{
                when(it){
                    is State.Success ->{
                        hideProgressBar()
                        it.data.let {specialityResponse ->
                            response = specialityResponse
                            specialityAdapter.submitList(specialityResponse)
                        }
                    }
                    is State.Loading ->{
                        showProgressBar()
                    }
                    is State.Error ->{
                        it.message.let {
                            Toast.makeText(
                                requireContext(),
                                "An error is $it",Toast.LENGTH_LONG)
                                .show()
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


    private fun clickOnSeeAllForDoctors(){
        binding.textViewSeeAllDoctors.setOnClickListener {
            val action: NavDirections? = doctorResponse?.let {
                HomeFragmentDirections.actionFirstFragmentToAllDoctorsFragment(
                    it
                )
            }
            if (action != null) {
                findNavController().navigate(action)
            }
        }
    }

    private fun clickOnSeeAllForSpeciality(){
        binding.textViewSeeAllSpeciality.setOnClickListener {
            val action: NavDirections? = response?.let {
                HomeFragmentDirections.actionFirstFragmentToAllSpecialityFragment(
                    it
                )
            }
            if (action != null) {
                findNavController().navigate(action)
            }
        }

    }

    override fun onItemClick(doctor: Doctor) {
      val action: NavDirections? = doctor?.let {
          HomeFragmentDirections.actionFirstFragmentToDoctorProfileFragment(it)
      }
        if (action != null) {
            findNavController().navigate(action)
        }
    }

}