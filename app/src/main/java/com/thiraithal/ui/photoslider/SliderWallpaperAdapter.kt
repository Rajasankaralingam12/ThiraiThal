package com.thiraithal.ui.photoslider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.thiraithal.R
import com.thiraithal.model.PopularWallpaperModel

class SliderWallpaperAdapter(private val mContext: Context, pImageView: ImageView) : PagerAdapter(){

    var popularWallpaperModel = mutableListOf<PopularWallpaperModel>()
    var imageViewBG: ImageView

    init {
        this.imageViewBG = pImageView;
    }

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
       // val modelObject = Model.values()[position]
        val inflater = LayoutInflater.from(mContext)
        val layout = inflater.inflate(R.layout.adpter_slider, collection, false) as ViewGroup

        val uploaderName : TextView = layout.findViewById(R.id.tvWallpaperOwned)
        uploaderName.setText(popularWallpaperModel.get(position).wallpaperName)
        val imageView :ImageView = layout.findViewById(R.id.slideWallpaper)
        Glide.with(mContext).load(popularWallpaperModel.get(position).imageUrl).into(imageView)
        collection.addView(layout)

        return layout
    }

    fun addSlidingWallpapers(popularWallpaperModel: PopularWallpaperModel) {
        this.popularWallpaperModel.add(0,popularWallpaperModel)
        notifyDataSetChanged()
    }


    fun setSlidingWallpers(featureds: List<PopularWallpaperModel>, popularWallpaperModelBundle : PopularWallpaperModel) {
        this.popularWallpaperModel = featureds.toMutableList()

        popularWallpaperModel.removeAll {
            it.id == popularWallpaperModelBundle.id
        }
        this.popularWallpaperModel.add(0, popularWallpaperModelBundle)
        notifyDataSetChanged()
    }


    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }
    override fun getCount(): Int {
        return popularWallpaperModel.size
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }


}