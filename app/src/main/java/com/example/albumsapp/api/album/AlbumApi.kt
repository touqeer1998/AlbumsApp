package com.example.albumsapp.api.album

import com.example.albumsapp.modules.home.models.Album

fun interface AlbumApi {
    suspend fun fetchAlbums(): List<Album>
}