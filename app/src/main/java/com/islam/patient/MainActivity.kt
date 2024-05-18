package com.islam.patient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.islam.patient.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.
        findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.splashScreenFragment ->
                    binding.bottomNavigationView.visibility = View.GONE
                R.id.signInFragment ->
                    binding.bottomNavigationView.visibility = View.GONE
                R.id.viewPagerFragment ->
                    binding.bottomNavigationView.visibility = View.GONE
                R.id.signUpFragment ->
                    binding.bottomNavigationView.visibility = View.GONE
                R.id.resetPasswordFragment ->
                    binding.bottomNavigationView.visibility = View.GONE
                else ->
                    binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }
}