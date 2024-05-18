package com.islam.patient

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController


class SplashScreenFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            if (onBoardingFinished()){
                if(userLoggedIn()){
                    findNavController().navigate(R.id.action_splashScreenFragment_to_firstFragment)
                }
                else{
                    findNavController().navigate(R.id.action_splashScreenFragment_to_signInFragment)
                }
            }else
            {
                findNavController().navigate(R.id.action_splashScreenFragment_to_viewPagerFragment)
            }
        }, 3000)
    }

    private fun onBoardingFinished(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }

    private fun userLoggedIn(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("MyPrefs",Context.MODE_PRIVATE)
        return sharedPref.getBoolean("loggedInBefore",false)
    }

}