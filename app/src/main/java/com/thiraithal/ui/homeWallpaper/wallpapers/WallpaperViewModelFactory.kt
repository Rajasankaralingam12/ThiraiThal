package com.thiraithal.ui.homeWallpaper.wallpapers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thiraithal.service.MainRepository

class WallpaperViewModelFactory constructor(private  val repository: MainRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(WallpaperViewModel::class.java)) {
            WallpaperViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}