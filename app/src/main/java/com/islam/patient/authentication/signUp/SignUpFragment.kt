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
    private var userId: String? = null
    private lateinit var patient: Patient
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
        observingSignUpStateflow()
        observingPatientState()

        }



    private fun clickOnCreateButton() {
        binding.buttonSignUp.setOnClickListener {
            val email = binding.textInputEditTextEmail.text.toString()
            val password = binding.textInputEditTextPassword.text.toString()
            val name = binding.textInputEditTextName.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {
                viewModel.signUp(email, password)
                patient = Patient(userId,name,email)
            }
        }
    }

    private fun observingSignUpStateflow(){
        lifecycleScope.launch {
            viewModel.signUpResult.collect{ result ->
                if (result?.isAuthenticated == true) {
                    findNavController().navigate(R.id.action_signUpFragment_to_firstFragment)
                    userId = firebaseAuth.currentUser?.uid
                    viewModel.addPatient(patient)
                } else {
                    result?.errorMessage?.let { showToast(it) }
                }
            }
        }
    }

    private fun observingPatientState(){
        lifecycleScope.launch {
            viewModel.patientState.collect{
                when(it){
                    is State.Success ->{
                        hideProgressBar()
                        showToast("your sign up is successful")
                    }
                    is State.Loading ->
                        showProgressBar()
                    is State.Error ->{
                        val errorMessage = it.message?: "unkown error"
                            showToast("An error occurred: $errorMessage")
                    }
                    else ->
                        showToast("any thing")
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

    private fun showToast(message: String){
        Toast.makeText(requireContext(),message, Toast.LENGTH_LONG).show()
    }

}

