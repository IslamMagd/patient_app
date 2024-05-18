package com.islam.patient

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.islam.domain.model.State
import com.islam.patient.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var doctorAdapter: DoctorAdapter
    lateinit var specialityAdapter: SpecialityAdapter
    private val viewModel: AppointmentViewModel by viewModels()



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
            addItemDecoration(SpacingSpecialityItemDecoration(20))
        }
    }

    private fun setUpDoctorsRecyclerView() {
        doctorAdapter = DoctorAdapter()
        binding.recyclerViewDoctor.apply {
            adapter = doctorAdapter
            addItemDecoration(SpacingDoctorItemDecoration(20))
        }
    }

    private fun observingDoctorsStateFlow(){
        lifecycleScope.launch {
            viewModel.doctorsStateFlow.collect{
                when(it){
                    is State.Success ->{
                        hideProgressBar()
                        it.data.let{doctorResponse ->
                            doctorAdapter.submitList(doctorResponse)
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
}