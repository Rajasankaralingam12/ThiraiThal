package com.thiraithal.ui.wallpapers.wallpapersViewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thiraithal.service.MainRepository
import com.thiraithal.ui.upload.uploadViewmodel.UploadViewModel

class WallpaperViewModelFactory constructor(private  val repository: MainRepository):ViewModelProvider.Factory{




    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return if (modelClass.isAssignableFrom(WallpaperViewModel::class.java)) {
            WallpaperViewModel(
                repository
            ) as T
        } else if (modelClass.isAssignableFrom(UploadViewModel::class.java)) {
            UploadViewModel(
                this.repository
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}