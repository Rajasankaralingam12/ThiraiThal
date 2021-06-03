package com.thiraithal.ui.homeWallpaper.wallpapers

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.thiraithal.R
import com.thiraithal.databinding.FragmentWallpapersBinding
import com.thiraithal.model.PopularWallpaperModel
import com.thiraithal.service.MainRepository
import com.thiraithal.service.RetrofitService
import com.thiraithal.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_wallpapers.view.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class WallpapersFragment : BaseFragment(), PopularWallpaperAdapter.IGetAllFeatureWallpapers {

    private val TAG = "WallpapersFragment"

    private val retrofitService = RetrofitService.getInstance()
    private lateinit var popularWallpaperAdapter: PopularWallpaperAdapter
    private lateinit var viewModel: WallpaperViewModel
    fun newInstance( title: String?): WallpapersFragment {
        val fragmentFirst =
            WallpapersFragment()
        return fragmentFirst
    }

    override fun getViewBinding(): ViewBinding {
        return FragmentWallpapersBinding.inflate(layoutInflater)
    }

    override fun getViewModel() {

        viewModel= ViewModelProvider(this, WallpaperViewModelFactory(MainRepository(retrofitService))).get(WallpaperViewModel::class.java)


    }

    override fun onFragmentCreated(view: View) {

        val pGridLayoutManager = GridLayoutManager(activity, 3)
        pGridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) 3 else 1
            }
        }
        popularWallpaperAdapter = PopularWallpaperAdapter(this)
        binding.root.rvPopularWallpaper.layoutManager = pGridLayoutManager;
        binding.root.rvPopularWallpaper.adapter = popularWallpaperAdapter
    }


    override fun onParentFragmentCreated(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, binding: ViewBinding) {
        val view : View = inflater.inflate(R.layout.fragment_wallpapers, container,false)
        linLayFragment.addView(view)
        showSearchView(false)


    }

    override fun onResume() {
        super.onResume()
      /*  var moviess: ArrayList<MovieModel> = ArrayList()
        val movieModel = MovieModel("Raja","sds.jpg","Student", "asc")
        var movieModel1  = MovieModel("Raja","sds.jpg","Student", "asc")
        moviess.add(movieModel)
        moviess.add(movieModel1)
*/
        getPopularWallpaperList(viewModel)

    }

    fun getAllFeatureList( viewModel: WallpaperViewModel, wallpaperAdapter: WallpaperAdapter){
        viewModel.featuresList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            if(null != it)
                wallpaperAdapter.setMovieList(it)

        })

        viewModel.errorMessage.observe(this, Observer {

        })
        viewModel.getAllFeatures()
    }

    fun getPopularWallpaperList( viewModel: WallpaperViewModel){
        viewModel.popularWallpaperModel.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            if(null != it)
            popularWallpaperAdapter.setMovieList(it)

           // addPopularImage(viewModel)
        })

        viewModel.errorMessage.observe(this, Observer {

        })
        viewModel.getPopularWallpapers()
    }


    fun addPopularImage( viewModel: WallpaperViewModel){
        val popularWallpaperModel = PopularWallpaperModel("1","Raja","https://howtodoandroid.com/images/terminator_2.jpg",true, "asc")

        viewModel.baseResponse.observe(this, Observer {
            Log.d(TAG, "Image Posted: ${it.responseMessage}")
        })

        viewModel.errorMessage.observe(this, Observer {

        })
        viewModel.addPopularImages(popularWallpaperModel)
    }

    override fun getAllFeatureList(wallpaperAdapter: WallpaperAdapter) {
        getAllFeatureList(viewModel, wallpaperAdapter)
    }


}