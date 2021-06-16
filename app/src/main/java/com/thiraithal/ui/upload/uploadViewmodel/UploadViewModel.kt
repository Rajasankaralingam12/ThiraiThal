package com.thiraithal.ui.upload.uploadViewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thiraithal.model.BaseResponse
import com.thiraithal.model.PopularWallpaperModel
import com.thiraithal.service.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UploadViewModel constructor(private val repository: MainRepository)  : ViewModel() {


    val errorMessage = MutableLiveData<String>()
    val baseResponse = MutableLiveData<BaseResponse>()



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