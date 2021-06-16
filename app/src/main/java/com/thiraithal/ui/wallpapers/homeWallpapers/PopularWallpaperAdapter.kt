package com.thiraithal.ui.wallpapers.homeWallpapers

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thiraithal.databinding.AdapterPopularWallpaperBinding
import com.thiraithal.databinding.PopularAdapterHeaderBinding
import com.thiraithal.model.PopularWallpaperModel
import com.thiraithal.ui.photoslider.WallpaperSliderActivity
import com.thiraithal.utils.SpacingItemDecorator

class PopularWallpaperAdapter( pGetAllFeatureWallpapers: IGetAllFeatureWallpapers, pGetCurrentPopularWallpapers : IGetCurrentPopularWallpapers, context: Context) : RecyclerView.Adapter< RecyclerView.ViewHolder >() {

     val TYPE_HEADER = 0
     val TYPE_ITEM = 1

    var context : Context;

    init {
        this.context = context
    }
    var iGetAllFeatureWallpapers : IGetAllFeatureWallpapers

      var  iGetCurrentPopularWallpapers : IGetCurrentPopularWallpapers

    interface IGetAllFeatureWallpapers{
        fun getAllFeatureList(wallpaperAdapter: WallpaperAdapter)
    }

    interface IGetCurrentPopularWallpapers{
        fun getClickedPopularWallpaper(popularWallpaperModel: PopularWallpaperModel)
    }

    init {
        this.iGetAllFeatureWallpapers =pGetAllFeatureWallpapers;
        this.iGetCurrentPopularWallpapers = pGetCurrentPopularWallpapers
    }

    var popularWallpaperModel = mutableListOf<PopularWallpaperModel>()


    fun setMovieList(featureds: List<PopularWallpaperModel>) {
        this.popularWallpaperModel = featureds.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  RecyclerView.ViewHolder  {

        if(viewType == TYPE_HEADER){
            val inflater = LayoutInflater.from(parent.context)
            val binding = PopularAdapterHeaderBinding.inflate(inflater, parent, false)
            return PopularHeaderWallpaperViewHolder(binding)
        }else   if(viewType == TYPE_ITEM){
            val inflater = LayoutInflater.from(parent.context)
            val binding = AdapterPopularWallpaperBinding.inflate(inflater, parent, false)
            return PopularWallpaperViewHolder(binding)
        } else {
            throw RuntimeException("there is no type that matches the type $viewType + make sure your using types correctly")
        }

    }

 /*   override fun onBindViewHolder(holder: PopularWallpaperViewHolder, position: Int) {
        val movie = popularWallpaperModel[position]
        Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)

    }*/

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    override fun getItemCount(): Int {
        return popularWallpaperModel.size+1
    }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            if (holder is PopularHeaderWallpaperViewHolder){

               val headerHolder : PopularHeaderWallpaperViewHolder = holder
                val adapter = WallpaperAdapter()
                val llm = LinearLayoutManager(headerHolder.binding.getRoot().getContext())
                llm.orientation = LinearLayoutManager.HORIZONTAL;
                headerHolder.binding.rvFeaturedWallpapers.layoutManager = llm
                val x = (headerHolder.binding.getRoot().getContext().resources.displayMetrics.density * 2).toInt()
                headerHolder.binding.rvFeaturedWallpapers.addItemDecoration(SpacingItemDecorator(x))
                headerHolder.binding.rvFeaturedWallpapers.adapter = adapter
                iGetAllFeatureWallpapers.getAllFeatureList(adapter)

            }
            else if (holder is PopularWallpaperViewHolder){
                val popularWallpaperViewHolder : PopularWallpaperViewHolder = holder
                val wallpaperModel = popularWallpaperModel[position-1]
                Glide.with(holder.itemView.context).load(wallpaperModel.imageUrl).into(popularWallpaperViewHolder.binding.imageview)

                popularWallpaperViewHolder.binding.imageview.setOnClickListener {
                    iGetCurrentPopularWallpapers.getClickedPopularWallpaper(wallpaperModel)

                }

            }

        }
    }


class PopularWallpaperViewHolder(val binding: AdapterPopularWallpaperBinding) : RecyclerView.ViewHolder(binding.root) {

}

class PopularHeaderWallpaperViewHolder(val binding: PopularAdapterHeaderBinding) : RecyclerView.ViewHolder(binding.root) {

}