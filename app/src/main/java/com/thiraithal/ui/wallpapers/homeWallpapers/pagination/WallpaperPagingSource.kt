package com.thiraithal.ui.wallpapers.homeWallpapers.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thiraithal.model.PopularWallpaperModel
import com.thiraithal.service.MainRepository
import com.thiraithal.service.RetrofitService

class WallpaperPagingSource (val apiService: MainRepository): PagingSource<Int, PopularWallpaperModel>() {

    var nextPageNumber: Int = 0

    private val retrofitService = RetrofitService.getInstance()

    override fun getRefreshKey(state: PagingState<Int, PopularWallpaperModel>): Int? {

        return state.anchorPosition

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularWallpaperModel> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = retrofitService.getPopularWallpapers2(nextPage, 10)

            nextPageNumber++
            /*   if(response?.get(0).info?.next != null) {
                   val uri = Uri.parse(response?.get(0).info?.next!!)
                   val nextPageQuery = uri.getQueryParameter("page")
                   nextPageNumber = nextPageQuery?.toInt()
               }*/

            var prevPageNumber: Int? = null
            /*    if(response?.get(0).info?.prev != null) {
                    val uri = Uri.parse(response?.get(0).info?.prev!!)
                    val prevPageQuery = uri.getQueryParameter("page")

                    prevPageNumber = prevPageQuery?.toInt()
                }*/


            if(response.isEmpty()){
                LoadResult.Page(data = response,
                    prevKey = null,
                    nextKey = null)
            }else{
                LoadResult.Page(data = response,
                    prevKey = null,
                    nextKey = nextPageNumber)
            }
        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }



}