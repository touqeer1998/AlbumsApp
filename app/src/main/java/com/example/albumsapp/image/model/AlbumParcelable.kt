package com.example.albumsapp.image.model

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize

@VersionedParcelize
data class AlbumParcelable(val title: String?, val url: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(), parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AlbumParcelable> {
        override fun createFromParcel(parcel: Parcel): AlbumParcelable {
            return AlbumParcelable(parcel)
        }

        override fun newArray(size: Int): Array<AlbumParcelable?> {
            return arrayOfNulls(size)
        }
    }
}
