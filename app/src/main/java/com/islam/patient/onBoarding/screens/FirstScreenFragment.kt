package com.islam.patient.onBoarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.islam.patient.R
import com.islam.patient.databinding.FragmentFirstScreenBinding


class FirstScreenFragment : Fragment() {
    lateinit var binding: FragmentFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager =  activity?.findViewById<ViewPager2>(R.id.viewPager)
        binding.textViewNext.setOnClickListener{
            viewPager?.currentItem = 1
        }
    }
}