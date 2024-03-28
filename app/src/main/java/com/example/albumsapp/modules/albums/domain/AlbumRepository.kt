package com.example.albumsapp.modules.albums.domain

import com.example.albumsapp.api.album.AlbumApi
import com.example.albumsapp.modules.albums.models.Album
import timber.log.Timber
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val albumApi: AlbumApi
) {

    suspend fun fetchAlbums(): List<Album> {
        try {
            return albumApi.fetchAlbums()
        } catch (e: Exception) {
            Timber.e(" :$e")
        }
        return emptyList()
    }

}