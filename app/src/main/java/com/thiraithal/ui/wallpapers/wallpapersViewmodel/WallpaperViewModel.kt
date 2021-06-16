package com.thiraithal.ui.wallpapers.wallpapersViewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.thiraithal.model.BaseResponse
import com.thiraithal.model.FeaturedModel
import com.thiraithal.model.PopularWallpaperModel
import com.thiraithal.service.MainRepository
import com.thiraithal.service.RetrofitService
import com.thiraithal.ui.wallpapers.homeWallpapers.pagination.WallpaperPagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WallpaperViewModel constructor( val repository: MainRepository)  : ViewModel() {


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

    fun getPopularWallpapers(initPage: Int, pageLimit : Int) {

        val response = repository.getPopularWallpapers(initPage, pageLimit)
        response.enqueue(object : Callback<List<PopularWallpaperModel>> {
            override fun onResponse(call: Call<List<PopularWallpaperModel>>, response: Response<List<PopularWallpaperModel>>) {
                popularWallpaperModel.postValue(response.body())
            }

            override fun onFailure(call: Call<List<PopularWallpaperModel>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }


    fun getPopularWallpapers2(): Flow<PagingData<PopularWallpaperModel>> {
        return Pager (config = PagingConfig(pageSize = 10, maxSize = 200),
            pagingSourceFactory = { WallpaperPagingSource(repository) }).flow.cachedIn(viewModelScope)
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