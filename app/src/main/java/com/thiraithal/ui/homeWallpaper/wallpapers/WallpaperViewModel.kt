package com.thiraithal.ui.homeWallpaper.wallpapers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thiraithal.model.BaseResponse
import com.thiraithal.model.FeaturedModel
import com.thiraithal.model.PopularWallpaperModel
import com.thiraithal.service.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WallpaperViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val featuresList = MutableLiveData<List<FeaturedModel>>()
    val popularWallpaperModel = MutableLiveData<List<PopularWallpaperModel>>()
    val errorMessage = MutableLiveData<String>()
    val baseResponse = MutableLiveData<BaseResponse>()

    fun getAllFeatures() {

        val response = repository.getAllMovies()
        response.enqueue(object : Callback<List<FeaturedModel>> {
            override fun onResponse(call: Call<List<FeaturedModel>>, response: Response<List<FeaturedModel>>) {
                featuresList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<FeaturedModel>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getPopularWallpapers() {

        val response = repository.getPopularWallpapers()
        response.enqueue(object : Callback<List<PopularWallpaperModel>> {
            override fun onResponse(call: Call<List<PopularWallpaperModel>>, response: Response<List<PopularWallpaperModel>>) {
                popularWallpaperModel.postValue(response.body())
            }

            override fun onFailure(call: Call<List<PopularWallpaperModel>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun addPopularImages(popularWallpaperModel: PopularWallpaperModel) {

        val response = repository.addPopularImages(popularWallpaperModel)
        response.enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                baseResponse.postValue(response.body())
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

}