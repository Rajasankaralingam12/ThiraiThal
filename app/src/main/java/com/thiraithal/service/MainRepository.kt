package com.thiraithal.service

import com.thiraithal.model.PopularWallpaperModel

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllMovies() = retrofitService.getAllMovies()

    fun getPopularWallpapers() = retrofitService.getPopularWallpapers()

    fun addPopularImages(pPopularWallpaperModel: PopularWallpaperModel) = retrofitService.addPopularImages(pPopularWallpaperModel)
}