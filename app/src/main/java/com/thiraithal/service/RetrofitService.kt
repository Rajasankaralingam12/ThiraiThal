package com.thiraithal.service

import com.thiraithal.model.FeaturedModel
import com.thiraithal.model.PopularWallpaperModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

/*@GET("movielist.json")
fun getAllMovies() : Call<List<MovieModel>>*/
@GET("FeaturedWallpapers")
fun getAllMovies() : Call<List<FeaturedModel>>

    @GET("FeaturedWallpapers")
    fun getPopularWallpapers() : Call<List<PopularWallpaperModel>>

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