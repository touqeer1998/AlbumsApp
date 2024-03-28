package com.example.albumsapp.modules.albums.utils

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.albumsapp.R
import com.example.albumsapp.databinding.RvAlbumsBinding
import com.example.albumsapp.modules.albums.models.Album
import timber.log.Timber

fun interface OnAlbumClick {
    fun onAlbumClick(album: Album)
}

class AlbumsAdapter : ListAdapter<Album, AlbumViewHolder>(AlbumsDiffCallback()) {

    private lateinit var listener: OnAlbumClick

    fun setOnLocationClickListener(mListener: OnAlbumClick) {
        listener = mListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = getItem(position)
        holder.bind(album)
    }

}

class AlbumViewHolder private constructor(
    private val binding: RvAlbumsBinding, private val listener: OnAlbumClick
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(album: Album) {
        binding.imageLoader.visibility = View.VISIBLE
        binding.ivThumbnail.visibility = View.GONE
        val placeHolder =
            ContextCompat.getDrawable(itemView.context, R.drawable.ic_launcher_background)
        binding.tvTitle.text = album.title
        Glide.with(itemView.context).load(album.thumbnailUrl)
            .apply(RequestOptions().placeholder(placeHolder))
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Timber.i("loading failed")
                    binding.imageLoader.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Timber.i("sucess")
                    binding.imageLoader.visibility = View.GONE
                    binding.ivThumbnail.visibility = View.VISIBLE
                    return false
                }
            }).into(binding.ivThumbnail)
        binding.root.setOnClickListener {
            listener.onAlbumClick(album)
        }
    }

    companion object {
        fun from(
            parent: ViewGroup, listener: OnAlbumClick
        ): AlbumViewHolder {
            val context = parent.context
            val inflater = LayoutInflater.from(context)
            val binding = RvAlbumsBinding.inflate(inflater, parent, false)
            return AlbumViewHolder(binding, listener)
        }
    }

}


class AlbumsDiffCallback : DiffUtil.ItemCallback<Album>() {
    override fun areItemsTheSame(
        oldItem: Album, newItem: Album
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Album, newItem: Album
    ): Boolean {
        return oldItem == newItem
    }
}

