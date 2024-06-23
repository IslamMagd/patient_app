package com.islam.patient.authentication.completeProfile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.islam.domain.model.Patient
import com.islam.domain.model.State
import com.islam.patient.R
import com.islam.patient.databinding.FragmentPatientProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.URI
import javax.inject.Inject

@AndroidEntryPoint
class PatientProfileFragment : Fragment() {

    @Inject lateinit var auth: FirebaseAuth
    lateinit var binding: FragmentPatientProfileBinding
    private lateinit var uid: String
    private lateinit var patient: Patient
    private var imageUri: Uri? = null
    private val viewModel: CompleteProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPatientProfileBinding.inflate(inflater,container,false)
        val gender = resources.getStringArray(R.array.gender)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.drop_down_item,gender)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        clickOnFloatingActionButton()
        clickOnCompleteProfileButtton()
//        observingUpdateProfileStateFlow()
        observingUpdatePatientState()
//        checkLoggedInState()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
         imageUri = data?.data
        binding.shapeableImageView.setImageURI(imageUri)
    }
    
    private fun clickOnFloatingActionButton(){
        binding.FloatingActionButton.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        }
    }
    

    private fun clickOnCompleteProfileButtton(){
        binding.buttonCompleteProfile.setOnClickListener {
//            val profileUpdates = UserProfileChangeRequest.Builder()
//                .setDisplayName(binding.textInputEditTextName.toString())
//                .setPhotoUri(imageUri)
//                .build()
//            viewModel.updateProfile(profileUpdates)
            uid = auth.currentUser!!.uid
            val phoneNumber = binding.textInputEditTextPhone.text.toString()
            val gender = binding.autoCompleteTextView.text.toString()
            val name = binding.textInputEditTextName.text.toString()
            patient = Patient( uid = uid, name = name,phoneNumber = phoneNumber, gender = gender)
            viewModel.updatePatient(patient)
        }
    }

    private fun observingUpdatePatientState(){
        lifecycleScope.launch {
            viewModel.updatePatientState.collect{
                when(it){
                    is State.Success ->{
                        hideProgressBar()
                        showToast("you completed profile successfully")
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
    
    private fun observingUpdateProfileStateFlow(){
        lifecycleScope.launch {
            viewModel.updatePorfileStateFlow.collect { result ->
                if (result?.isAuthenticated == true) {
                    showToast("You completed successfully!")

                } else {
                    result?.errorMessage?.let { showToast(it) }
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


    private fun checkLoggedInState(){
        val user = auth.currentUser
        if(user != null){
            binding.shapeableImageView.setImageURI(user.photoUrl)
        }
    }

}