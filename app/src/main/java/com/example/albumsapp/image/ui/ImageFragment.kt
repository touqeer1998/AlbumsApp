package com.example.albumsapp.image.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.albumsapp.R
import com.example.albumsapp.databinding.FragmentImageBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 * Use the [ImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ImageFragment : Fragment() {

    private val args: ImageFragmentArgs by navArgs()

    private var _binding: FragmentImageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val album = args.Album
        binding.title = album.title ?: ""
        val placeHolderUrl = "https://via.placeholder.com/600/771796"
        handleImage(album.url ?: placeHolderUrl)

        binding.btnShare.setOnClickListener {
            handleShareClick(album.url ?: placeHolderUrl)
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun handleShareClick(url: String) {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "image/*"
            shareIntent.putExtra(Intent.EXTRA_STREAM, url)
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Sharing an image")
            val chooser = Intent.createChooser(shareIntent, "Share Image")
            if (shareIntent.resolveActivity(requireContext().packageManager) != null) {
                startActivity(chooser)
            }
        } catch (e: Exception) {
            Timber.e("handleShareClick :$e")
        }
    }

    private fun handleImage(url: String) {
        val placeHolder =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_launcher_background)
        Glide.with(requireContext()).load(url).apply(RequestOptions().placeholder(placeHolder))
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Timber.i("image loading failed")
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Timber.i("image loading succeeded")
                    return false
                }
            }).into(binding.ivImage)
    }
}