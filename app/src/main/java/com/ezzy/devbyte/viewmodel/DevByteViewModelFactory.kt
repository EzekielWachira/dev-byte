package com.ezzy.devbyte.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class DevByteViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DevbyteViewModel::class.java)){
            return DevbyteViewModel(application) as T
        }
        throw IllegalArgumentException("unable to construct view model")
    }
}