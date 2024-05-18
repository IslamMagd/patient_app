package com.islam.patient.authentication.signIn

import android.app.Activity.RESULT_CANCELED
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.islam.patient.R
import com.islam.patient.databinding.FragmentSignInBinding
import com.islam.patient.util.REQUEST_CODE_SIGN_IN
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel : SignInViewModel by viewModels()
    @Inject lateinit var signInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickOnSIgnUpTextView()

        clickOnSignInButton()
        observingSignInStateflow()

        clickOnSignInWithGoogleButton()
        observingSignInWithGoogleStateflow()

        clickOnForgetPassword()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== REQUEST_CODE_SIGN_IN) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(data).result
            account?.let {
                viewModel.signInwithGoogleResult(it)
            }
        }
    }



    private fun clickOnSIgnUpTextView(){
        binding.textViewSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }



    private fun clickOnSignInButton() {
        binding.buttonSignIn.setOnClickListener {
            val email = binding.textInputEditTextEmail.text.toString()
            val password = binding.textInputEditTextPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty())
                viewModel.signInWithEmailAndPassword(email, password)
            userLoggedIn()
        }
    }

    private fun observingSignInStateflow(){
        lifecycleScope.launch {
            viewModel.signInResult.collect { result ->
                if (result?.isAuthenticated == true) {
                   showToast("You logged in successfully!")
                    findNavController().navigate(R.id.action_signInFragment_to_firstFragment)
                } else {
                    result?.errorMessage?.let { showToast(it) }
                }
            }
        }
    }

    private fun clickOnForgetPassword(){
        binding.textViewForgetPassword.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_resetPasswordFragment)
        }
    }



    private fun clickOnSignInWithGoogleButton() {
        binding.imageViewGoogle.setOnClickListener {
            signInClient.signInIntent.also {
                startActivityForResult(it, REQUEST_CODE_SIGN_IN)
            }
            userLoggedIn()
        }
    }

    private fun observingSignInWithGoogleStateflow(){
                lifecycleScope.launch {
                    viewModel.signInWithGoogleResult.collect {result ->
                        if (result?.isAuthenticated == true) {
                            findNavController()
                                .navigate(R.id.action_signInFragment_to_firstFragment)
                            showToast("You logged in successfully gogo")
                        } else {
                            Log.i("ahmed",result?.errorMessage.toString())
                            result?.errorMessage?.let { showToast(it) }
                        }
                    }
                }
    }

    private fun showToast(message: String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }


    private fun userLoggedIn(){
        val sharedPref = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("loggedInBefore",true)
        editor.apply()
    }

}