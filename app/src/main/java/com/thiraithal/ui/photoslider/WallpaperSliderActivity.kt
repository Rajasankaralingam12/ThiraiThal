package com.thiraithal.ui.photoslider

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.thiraithal.R
import com.thiraithal.model.PopularWallpaperModel
import com.thiraithal.service.MainRepository
import com.thiraithal.service.RetrofitService
import com.thiraithal.ui.wallpapers.wallpapersViewmodel.WallpaperViewModel
import com.thiraithal.ui.wallpapers.wallpapersViewmodel.WallpaperViewModelFactory
import com.thiraithal.utils.BlurTransformation
import kotlinx.android.synthetic.main.activity_wallpaper_slider.view.*

class WallpaperSliderActivity : AppCompatActivity() {

    private lateinit var activityWallpaperSliderBinding: ViewBinding
    private lateinit var view :View
    private lateinit var sliderWallpaperAdapter: SliderWallpaperAdapter
    private lateinit var viewModel: WallpaperViewModel
    private val retrofitService = RetrofitService.getInstance()
    private lateinit var popularWallpaperModelBundle : PopularWallpaperModel
    private lateinit var popularWallperList: List<PopularWallpaperModel>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         popularWallpaperModelBundle = intent.extras?.getParcelable("wallpaper")!!
        activityWallpaperSliderBinding = DataBindingUtil.setContentView(this, R.layout.activity_wallpaper_slider)
        view = activityWallpaperSliderBinding.root
        viewModel= ViewModelProvider(this,
            WallpaperViewModelFactory(
                MainRepository(retrofitService)
            )
        ).get(WallpaperViewModel::class.java)


        view.ivBackButton.setOnClickListener({
            onBackPressed()
        })

        view.viewPager.clipToPadding = false
        view.viewPager.setPadding(60, 0, 60, 0);
        view.viewPager.setPageMargin(-40);
        sliderWallpaperAdapter = SliderWallpaperAdapter(this, view.ivSliderBackground)
        view.viewPager.adapter = sliderWallpaperAdapter
       // sliderWallpaperAdapter.addSlidingWallpapers(popularWallpaperModelBundle)
        Glide.with(this@WallpaperSliderActivity).load(popularWallpaperModelBundle.imageUrl)
            .apply( RequestOptions().transform( BlurTransformation(this@WallpaperSliderActivity)))
            .into(view.ivSliderBackground)
        getPopularWallpaperList(viewModel)
        view.viewPager?.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {

                Glide.with(this@WallpaperSliderActivity).load(popularWallperList.get(position).imageUrl)
                    .apply( RequestOptions().transform( BlurTransformation(this@WallpaperSliderActivity)))
                    .into(view.ivSliderBackground)
            }
        })
    }



    private fun getPopularWallpaperList(viewModel: WallpaperViewModel){

        viewModel.popularWallpaperModel.observe(this, androidx.lifecycle.Observer {
            Log.d("TAG", "onCreate: $it")
            if(null != it){
                popularWallperList = it
                sliderWallpaperAdapter.setSlidingWallpers(it, popularWallpaperModelBundle)
            }
        })

        viewModel.errorMessage.observe(this, androidx.lifecycle.Observer {
            Log.d("TAG", "something went wrong")
        })

        viewModel.getPopularWallpapers(1,10)
    }

}