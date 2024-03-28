package com.example.albumsapp.api.album

import com.example.albumsapp.api.BaseApiService
import com.example.albumsapp.modules.home.models.Album
import timber.log.Timber
import javax.inject.Inject

class AlbumApiImplementation @Inject constructor(
    private val baseApiService: BaseApiService
) : AlbumApi {
    override suspend fun fetchAlbums(): List<Album> {
        try {
            baseApiService.fetchAlbums().let {
                it.body()?.also { albums ->
                    return albums
                }
            }
        } catch (e: Exception) {
            Timber.e("fetchAlbums :$e")
        }
        return emptyList()
    }
}