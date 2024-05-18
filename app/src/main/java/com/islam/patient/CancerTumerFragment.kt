package com.islam.patient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.islam.patient.databinding.FragmentCancerTumerBinding


class CancerTumerFragment : Fragment() {

    private lateinit var binding: FragmentCancerTumerBinding
    private lateinit var pickImageLauncher: ActivityResultLauncher<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCancerTumerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null)
                binding.imageViewRay.setImageURI(uri)
        }

        clickOnselectImageButton()
    }

    private fun clickOnselectImageButton() {
        binding.buttonSelectImage.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }
    }
}