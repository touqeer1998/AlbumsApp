package com.example.albumsapp.modules.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albumsapp.modules.home.domain.AlbumRepository
import com.example.albumsapp.modules.home.models.Album
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val albumRepository: AlbumRepository
) : ViewModel() {

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    fun fetchAlbums() = viewModelScope.launch {
        try {
            albumRepository.fetchAlbums().let {
                _albums.postValue(it)
            }
        } catch (e: Exception) {
            Timber.e("fetchAlbums :$e")
        }
    }

}