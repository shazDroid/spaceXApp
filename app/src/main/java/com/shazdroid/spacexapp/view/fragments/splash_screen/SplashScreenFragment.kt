package com.shazdroid.spacexapp.view.fragments.splash_screen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.fragment.findNavController
import com.shazdroid.spacexapp.R
import com.shazdroid.spacexapp.base.BaseFragment
import com.shazdroid.spacexapp.databinding.FragmentSplashScreenBinding


class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding>() {
    override fun getFragmentLayout() = R.layout.fragment_splash_screen

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // call rocket list fragment after 1500 milli sec
        dismissSplashScreen()
    }

    private fun dismissSplashScreen() {
        Handler(Looper.myLooper()!!).postDelayed({
            findNavController().navigate(R.id.rocketListFragment)
        }, 4000)
    }
}