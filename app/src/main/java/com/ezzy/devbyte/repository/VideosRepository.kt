package com.ezzy.devbyte.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.ezzy.devbyte.database.VideosDatabase
import com.ezzy.devbyte.database.asDomainModel
import com.ezzy.devbyte.domain.models.Video
import com.ezzy.devbyte.network.Network
import com.ezzy.devbyte.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideosRepository(private val database: VideosDatabase) {

    private val TAG = "VideosRepository"

    val videos: LiveData<List<Video>> = Transformations.map(
        database.videoDao.getVideos()
    ) {
        it.asDomainModel()
    }

    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            val playlist = Network.devbytes.getPlayList().await()
            database.videoDao.insertAll(*playlist.asDatabaseModel())
        }
    }
}