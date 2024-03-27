package com.example.albumsapp.modules.albums.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsapp.databinding.RvAlbumsBinding
import com.example.albumsapp.modules.albums.models.Album

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
        binding.tvTitle.text = album.title
        binding.ivThumbnail.setImageURI(album.thumbnailUrl.toUri())

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

