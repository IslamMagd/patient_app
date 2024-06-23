package com.islam.patient.appointment.doctor.searchDoctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.islam.domain.model.State
import com.islam.patient.appointment.doctor.adapters.DoctorAdapter
import com.islam.patient.databinding.FragmentSearchDoctorBinding
import com.islam.patient.util.SEARCH_Doctor_TIME_DELAY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchDoctorFragment : Fragment() {
    private lateinit var binding: FragmentSearchDoctorBinding
    private lateinit var doctorAdapter: DoctorAdapter
    private val viewModel: SearchDoctorViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchDoctorBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpDoctorsRecyclerView()
        var job: Job? = null
        binding.editTextSearch.addTextChangedListener {editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_Doctor_TIME_DELAY)
                editable.let {
                    if(editable.toString().isNotEmpty()){
                        viewModel.getSearchDoctor(editable.toString())
                    }
                }
            }
        }

        observingDoctorsStateFlow()
    }

    private fun setUpDoctorsRecyclerView() {
        doctorAdapter = DoctorAdapter()
        binding.recyclerViewDoctor.adapter = doctorAdapter
    }

    private fun observingDoctorsStateFlow(){
        lifecycleScope.launch {
            viewModel.searchDoctorStateFlow.collect{
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
                                "An error is $it", Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    else -> {
                        Toast.makeText(requireContext(),"any thing", Toast.LENGTH_LONG).show()
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