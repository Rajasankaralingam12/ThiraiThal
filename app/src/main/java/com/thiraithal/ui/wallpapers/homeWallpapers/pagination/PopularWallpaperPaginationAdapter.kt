package com.thiraithal.ui.wallpapers.homeWallpapers.pagination

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thiraithal.databinding.AdapterPopularWallpaperBinding
import com.thiraithal.databinding.PopularAdapterHeaderBinding
import com.thiraithal.model.PopularWallpaperModel
import com.thiraithal.ui.wallpapers.homeWallpapers.WallpaperAdapter
import com.thiraithal.utils.SpacingItemDecorator
import kotlinx.android.synthetic.main.adapter_popular_wallpaper.view.*

class PopularWallpaperPaginationAdapter ( pGetAllFeatureWallpapers: IGetAllFeatureWallpapers, pGetCurrentPopularWallpapers : IGetCurrentPopularWallpapers, context: Context) : PagingDataAdapter<PopularWallpaperModel, RecyclerView.ViewHolder>(DiffUtilCallBack()) {

    val TYPE_HEADER = 0
    val TYPE_ITEM = 1

    var context: Context;

    init {
        this.context = context
    }

    var iGetAllFeatureWallpapers: IGetAllFeatureWallpapers

    var iGetCurrentPopularWallpapers: IGetCurrentPopularWallpapers

    interface IGetAllFeatureWallpapers {
        fun getAllFeatureList(wallpaperAdapter: WallpaperAdapter)
    }

    interface IGetCurrentPopularWallpapers {
        fun getClickedPopularWallpaper(popularWallpaperModel: PopularWallpaperModel)
    }

    init {
        this.iGetAllFeatureWallpapers = pGetAllFeatureWallpapers;
        this.iGetCurrentPopularWallpapers = pGetCurrentPopularWallpapers
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == TYPE_HEADER) {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PopularAdapterHeaderBinding.inflate(inflater, parent, false)
            return PopularHeaderWallpaperViewHolder(binding)
        } else if (viewType == TYPE_ITEM) {
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


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is PopularHeaderWallpaperViewHolder) {

            val headerHolder: PopularHeaderWallpaperViewHolder = holder
            val adapter = WallpaperAdapter()
            val llm = LinearLayoutManager(headerHolder.binding.getRoot().getContext())
            llm.orientation = LinearLayoutManager.HORIZONTAL;
            headerHolder.binding.rvFeaturedWallpapers.layoutManager = llm
            val x = (headerHolder.binding.getRoot()
                .getContext().resources.displayMetrics.density * 2).toInt()
            headerHolder.binding.rvFeaturedWallpapers.addItemDecoration(SpacingItemDecorator(x))
            headerHolder.binding.rvFeaturedWallpapers.adapter = adapter
            iGetAllFeatureWallpapers.getAllFeatureList(adapter)

        } else if (holder is PopularWallpaperViewHolder) {

            val popularWallpaperViewHolder: PopularWallpaperViewHolder = holder
            holder.bind(getItem(position - 1)!!)
            //  Glide.with(holder.itemView.context).load(wallpaperModel.imageUrl).into(popularWallpaperViewHolder.binding.imageview)

            popularWallpaperViewHolder.binding.imageview.setOnClickListener {
                getItem(position - 1)?.let { it1 ->
                    iGetCurrentPopularWallpapers.getClickedPopularWallpaper(
                        it1
                    )
                }

            }

        }


    }


    class PopularWallpaperViewHolder(val binding: AdapterPopularWallpaperBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(data: PopularWallpaperModel) {

            Glide.with(binding.root.context)
                .load(data.imageUrl)
                .into(binding.root.imageview)


            /*   binding.imageview.setOnClickListener {
            iGetCurrentPopularWallpapers.getClickedPopularWallpaper(data)*/

        }

    }


    class PopularHeaderWallpaperViewHolder(val binding: PopularAdapterHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<PopularWallpaperModel>() {
        override fun areItemsTheSame(
            oldItem: PopularWallpaperModel,
            newItem: PopularWallpaperModel
        ): Boolean {
            return oldItem.wallpaperName == newItem.wallpaperName
        }

        override fun areContentsTheSame(
            oldItem: PopularWallpaperModel,
            newItem: PopularWallpaperModel
        ): Boolean {
            return oldItem.wallpaperName == newItem.wallpaperName
                    && oldItem.id == newItem.id
        }

    }
}