package com.thiraithal.service

import com.thiraithal.model.BaseResponse
import com.thiraithal.model.FeaturedModel
import com.thiraithal.model.PopularWallpaperModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

@GET("FeaturedWallpapers")
fun getAllMovies() : Call<List<FeaturedModel>>

    @GET("popularWallpapers")
    fun getPopularWallpapers() : Call<List<PopularWallpaperModel>>

    @POST("popularWallpapers")
    fun addPopularImages(@Body popularWallpaperModel: PopularWallpaperModel) : Call<BaseResponse>

companion object {

    var retrofitService: RetrofitService? = null

    fun getInstance() : RetrofitService {

        if (retrofitService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://60ae52e75b8c300017dea3ab.mockapi.io/mobileApi/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitService = retrofit.create(RetrofitService::class.java)
        }
        return retrofitService!!
    }
}
}