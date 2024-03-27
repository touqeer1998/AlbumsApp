package com.example.albumsapp.modules.albums.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.albumsapp.MainActivity
import com.example.albumsapp.R
import com.example.albumsapp.databinding.FragmentSplashBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private lateinit var countDownTimer: CountDownTimer

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Starting countdown from 5 seconds and update TextView
        countDownTimer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000 + 1
                binding.tvCountDown.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                // Timer finished, do any necessary actions here
                navigateToHome()
                // removing the fragment
//                activity?.supportFragmentManager?.beginTransaction()?.remove(this@SplashFragment)
//                    ?.commitAllowingStateLoss()
            }
        }.start()

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideToolBar()
    }

    private fun navigateToHome() {
        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer.cancel()
        _binding = null
    }
}