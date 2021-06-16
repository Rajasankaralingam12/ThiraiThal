package com.thiraithal.service

import com.thiraithal.model.BaseResponse
import com.thiraithal.model.FeaturedModel
import com.thiraithal.model.PopularWallpaperModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface RetrofitService {

    @GET("FeaturedWallpapers")
    fun getAllMovies() : Call<List<FeaturedModel>>

    @GET("popularWallpapers")
    fun getPopularWallpapers(@Query("page") initPage: Int, @Query("limit") limit: Int) : Call<List<PopularWallpaperModel>>

    @GET("popularWallpapers")
    suspend fun getPopularWallpapers2(@Query("page") initPage: Int, @Query("limit") limit: Int) : List<PopularWallpaperModel>

    @GET("popularWallpapers")
    suspend fun getTop(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): ListingResponse

    class ListingResponse(val data: ListingData)

    class ListingData(
        val children: List<PopularWallpaperModelClass>,
        val after: String?,
        val before: String?
    )

    data class PopularWallpaperModelClass(val data: PopularWallpaperModel)

    @POST("popularWallpapers")
    fun addPopularImages(@Body popularWallpaperModel: PopularWallpaperModel) : Call<BaseResponse>

companion object {

    var retrofitService: RetrofitService? = null

    fun getInstance() : RetrofitService {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        if (retrofitService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://60ae52e75b8c300017dea3ab.mockapi.io/mobileApi/v1/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitService = retrofit.create(RetrofitService::class.java)
        }
        return retrofitService!!
    }
}
}