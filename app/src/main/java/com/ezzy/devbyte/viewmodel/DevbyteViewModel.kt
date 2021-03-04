package com.ezzy.devbyte.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ezzy.devbyte.database.getDatabase
import com.ezzy.devbyte.repository.VideosRepository
import kotlinx.coroutines.launch

class DevbyteViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val videosRepository = VideosRepository(database)

    init {
        viewModelScope.launch {
            videosRepository.refreshVideos()
        }
    }

    val playList = videosRepository.videos

}