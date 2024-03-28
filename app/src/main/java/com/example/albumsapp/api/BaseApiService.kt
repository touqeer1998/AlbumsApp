package com.example.albumsapp.api

import com.example.albumsapp.modules.home.models.Album
import com.example.albumsapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

fun interface BaseApiService {

    @GET(Constants.ALBUMS)
    suspend fun fetchAlbums(): Response<List<Album>>
}