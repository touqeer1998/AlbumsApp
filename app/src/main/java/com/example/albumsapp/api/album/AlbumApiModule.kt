package com.example.albumsapp.api.album

import com.example.albumsapp.api.BaseApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AlbumApiModule {

    @Provides
    fun provideAlbumApi(baseApiService: BaseApiService): AlbumApi {
        return AlbumApiImplementation(baseApiService)
    }

}