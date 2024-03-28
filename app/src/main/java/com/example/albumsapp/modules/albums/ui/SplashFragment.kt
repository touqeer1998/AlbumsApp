package com.example.albumsapp.modules.albums.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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
    private val albumViewModel: AlbumViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //making network class here in splash screen for better user experience on home screen
        albumViewModel.fetchAlbums()

        // Starting countdown from 5 seconds and update TextView
        countDownTimer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000 + 1
                binding.tvCountDown.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                // Timer finished, now navigating to home screen
                navigateToHome()
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