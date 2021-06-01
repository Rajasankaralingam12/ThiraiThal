package com.thiraithal.service

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllMovies() = retrofitService.getAllMovies()

    fun getPopularWallpapers() = retrofitService.getPopularWallpapers()
}