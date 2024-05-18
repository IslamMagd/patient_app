package com.islam.patient.authentication.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.islam.patient.R
import com.islam.patient.authentication.signIn.SignInViewModel
import com.islam.patient.databinding.FragmentSettingsBinding
import com.islam.patient.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private val viewModel : SettingsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewProfile.setOnClickListener {
            findNavController().navigate(R.id.action_fourthFragment_to_patientProfileFragment)
        }

        clickOnSignOutTextView()
        observeOnsignOtResultStateFlow()
    }

    private fun clickOnSignOutTextView(){
        binding.textViewSignOut.setOnClickListener {
            viewModel.signOut()
            findNavController().navigate(R.id.action_fourthFragment_to_signInFragment)

        }
    }

    private fun observeOnsignOtResultStateFlow(){
        lifecycleScope.launch {
            viewModel.signOtResultStateFlow.collect { result ->
                if (result?.isAuthenticated == true) {
                    showToast("You signd out")
                } else {
                    result?.errorMessage?.let { showToast(it) }
                }
            }
        }
    }

    private fun showToast(message: String){
        Toast.makeText(requireContext(),message, Toast.LENGTH_LONG).show()
    }


}