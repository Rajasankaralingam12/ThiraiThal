package com.thiraithal.ui.wallpapers.homeWallpapers

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewbinding.ViewBinding
import com.thiraithal.databinding.FragmentWallpapersBinding
import com.thiraithal.model.PopularWallpaperModel
import com.thiraithal.service.MainRepository
import com.thiraithal.service.RetrofitService
import com.thiraithal.ui.base.BaseFragment
import com.thiraithal.ui.photoslider.WallpaperSliderActivity
import com.thiraithal.ui.wallpapers.homeWallpapers.pagination.PopularWallpaperPaginationAdapter
import com.thiraithal.ui.wallpapers.wallpapersViewmodel.WallpaperViewModel
import com.thiraithal.ui.wallpapers.wallpapersViewmodel.WallpaperViewModelFactory
import com.thiraithal.utils.SpacingItemDecorator
import kotlinx.android.synthetic.main.fragment_test_base.view.*
import kotlinx.android.synthetic.main.fragment_wallpapers.view.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class WallpapersFragment : BaseFragment(), PopularWallpaperPaginationAdapter.IGetAllFeatureWallpapers,
    PopularWallpaperPaginationAdapter.IGetCurrentPopularWallpapers{

    init {
        lifecycleScope.launch {
            whenStarted {

            }
        }
    }

    private val TAG = "WallpapersFragment"

    private val retrofitService = RetrofitService.getInstance()

    private  lateinit var wallpapersBinding: FragmentWallpapersBinding;
    private lateinit var popularWallpaperAdapter: PopularWallpaperPaginationAdapter
   private lateinit var viewModel: WallpaperViewModel

    private val pageLimit : Int = 10;
    private  var initPage : Int = 1;



    fun newInstance( title: String?): WallpapersFragment {
        val fragmentFirst =
            WallpapersFragment()
        return fragmentFirst
    }




    override fun onFragmentCreated(view: View) {



        viewModel= ViewModelProvider(this,
            WallpaperViewModelFactory(
                MainRepository(retrofitService)
            )
        ).get(WallpaperViewModel::class.java)



        val pGridLayoutManager = GridLayoutManager(activity, 3)
        pGridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) 3 else 1
            }
        }
        popularWallpaperAdapter = context?.let { PopularWallpaperPaginationAdapter(this, this, it) }!!
        wallpapersBinding.root.rvPopularWallpaper.layoutManager = pGridLayoutManager;
        val x = (resources.displayMetrics.density *2).toInt() //converting dp to pixels
        wallpapersBinding.root.rvPopularWallpaper.addItemDecoration(SpacingItemDecorator(x))
        wallpapersBinding.root.rvPopularWallpaper.adapter = popularWallpaperAdapter

        initViewModel()

    }


    override fun onParentFragmentCreated(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, binding: ViewBinding) {
        wallpapersBinding = FragmentWallpapersBinding.inflate(layoutInflater)
        mainViewBinding.root.llview.addView(wallpapersBinding.root)
        showSearchView(false)
        linearLayoutVisibility(false)
        progressBarVisibility(true)


    }

    override fun onResume() {
        super.onResume()
      /*  var moviess: ArrayList<MovieModel> = ArrayList()
        val movieModel = MovieModel("Raja","sds.jpg","Student", "asc")
        var movieModel1  = MovieModel("Raja","sds.jpg","Student", "asc")
        moviess.add(movieModel)
        moviess.add(movieModel1)
*/
      //  getPopularWallpaperList(viewModel, initPage, pageLimit)

    }

    private fun initViewModel() {
        progressBarVisibility(true)
        linearLayoutVisibility(false)
        lifecycleScope.launchWhenResumed {
            viewModel.getPopularWallpapers2().collectLatest {
                linearLayoutVisibility(true)
                progressBarVisibility(false)
                popularWallpaperAdapter.submitData(it)
            }


        }
    }

    fun getAllFeatureList(viewModel: WallpaperViewModel, wallpaperAdapter: WallpaperAdapter){
        viewModel.featuresList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            if(null != it)
                wallpaperAdapter.setMovieList(it)

        })

        viewModel.errorMessage.observe(this, Observer {

        })
        viewModel.getAllFeatures()
    }

    private fun getPopularWallpaperList(viewModel: WallpaperViewModel, initPage: Int, pageLimit : Int){
        viewModel.popularWallpaperModel.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreate: $it")
            if(null != it){
                linearLayoutVisibility(true)
                progressBarVisibility(false)
             //   popularWallpaperAdapter.setMovieList(it)

            }

        })

        viewModel.errorMessage.observe(this, Observer {
            progressBarVisibility(false)
        })

/*        viewLifecycleOwner.lifecycleScope.launch{
            viewModel.posts.collectLatest {
                linearLayoutVisibility(true)
                progressBarVisibility(false)
                postsAdapter.submitData(it)

            }
        }*/

        viewModel.getPopularWallpapers(initPage, pageLimit)

    }

    override fun getAllFeatureList(wallpaperAdapter: WallpaperAdapter) {
        getAllFeatureList(viewModel, wallpaperAdapter)
    }

    override fun getClickedPopularWallpaper(popularWallpaperModel: PopularWallpaperModel) {
        val intent =  Intent(activity, WallpaperSliderActivity::class.java)
        val b = Bundle()
        b.putParcelable("wallpaper", popularWallpaperModel)
        intent.putExtras(b)
        startActivity(intent)
    }


}