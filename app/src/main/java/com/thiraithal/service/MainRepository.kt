package com.thiraithal.service

import com.thiraithal.model.PopularWallpaperModel

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllMovies() = retrofitService.getAllMovies()

    fun getPopularWallpapers(initPage: Int, pageLimit: Int) = retrofitService.getPopularWallpapers(initPage, pageLimit)



    fun addPopularImages(pPopularWallpaperModel: PopularWallpaperModel) = retrofitService.addPopularImages(pPopularWallpaperModel)
}