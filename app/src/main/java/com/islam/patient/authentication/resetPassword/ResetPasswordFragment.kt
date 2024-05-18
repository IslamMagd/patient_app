package com.islam.patient.authentication.resetPassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.islam.patient.R
import com.islam.patient.authentication.signUp.SignUpViewModel
import com.islam.patient.databinding.FragmentResetPasswordBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ResetPasswordFragment : Fragment() {

   lateinit var binding: FragmentResetPasswordBinding
    private val viewModel: ResetPasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResetPasswordBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickOnRestPasswordButton()
    }

    private fun clickOnRestPasswordButton() {
        binding.btnResetPassword.setOnClickListener {
            val email = binding.etEmail.text.toString()
            if (email.isNotEmpty()) {
                viewModel.resetPassword(email)
            }
        }
    }

    private fun observingResetPasswordStateflow(){
    lifecycleScope.launch {
            viewModel.restpasswordRsult.collect{ result ->
                if (result?.isAuthenticated == true) {
                    showToast("please check your mail")
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