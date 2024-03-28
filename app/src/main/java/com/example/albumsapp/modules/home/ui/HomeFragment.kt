package com.example.albumsapp.modules.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.albumsapp.R
import com.example.albumsapp.databinding.FragmentHomeBinding
import com.example.albumsapp.modules.home.utils.AlbumsAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val albumViewModel: AlbumViewModel by activityViewModels()

    private lateinit var albumAdapter: AlbumsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        albumAdapter = AlbumsAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //adapters
        setupAlbumsAdapter()

        //observers
        observeAlbums()
    }

    private fun observeAlbums() {
        albumViewModel.albums.observe(viewLifecycleOwner) {
            it?.let {
                albumAdapter.submitList(it)
            }
        }
    }

    private fun setupAlbumsAdapter() {
        binding.rvAlbums.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAlbums.adapter = albumAdapter

        albumAdapter.setOnLocationClickListener {
            navigateToImageFragment()
        }
    }

    private fun navigateToImageFragment() {
        findNavController().navigate(R.id.action_homeFragment_to_imageFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}