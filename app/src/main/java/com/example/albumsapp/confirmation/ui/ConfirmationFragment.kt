package com.example.albumsapp.confirmation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.albumsapp.MainActivity
import com.example.albumsapp.R
import com.example.albumsapp.databinding.FragmentConfirmationBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ConfirmationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConfirmationFragment : Fragment() {

    private var _binding: FragmentConfirmationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        _binding = FragmentConfirmationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnExit.setOnClickListener {
            closeApp()
        }

        binding.btnHome.setOnClickListener {
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(R.id.action_confirmationFragment_to_homeFragment2)
    }

    private fun closeApp() {
        (activity as MainActivity).finish()
    }
}