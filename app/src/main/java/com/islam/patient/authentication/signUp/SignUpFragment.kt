package com.islam.patient.authentication.signUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.islam.domain.model.Patient
import com.islam.domain.model.State
import com.islam.patient.R
import com.islam.patient.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModels()
    private var uid: String? = null
//    private lateinit var patient: Patient
//    private lateinit var email: String
//    private lateinit var name: String
    @Inject lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickOnCreateButton()
        observingregisterPatienttateflow()
        }



    private fun clickOnCreateButton() {
        binding.buttonSignUp.setOnClickListener {
            val email = binding.textInputEditTextEmail.text.toString()
            val password = binding.textInputEditTextPassword.text.toString()
            val name = binding.textInputEditTextName.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {
               val patient = Patient(name,email,password)
                viewModel.registerPatient(patient)
            }
        }
    }

    private fun observingregisterPatienttateflow(){
        lifecycleScope.launch {
            viewModel.registerPatient.collect{ result ->
                if (result?.isAuthenticated == true) {
                    showToast("You registered successfully!")
                    findNavController().navigate(R.id.action_signUpFragment_to_firstFragment)
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

