package com.thiraithal.ui.homeWallpaper.wallpapers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thiraithal.databinding.AdapterPopularWallpaperBinding
import com.thiraithal.model.PopularWallpaperModel

class PopularWallpaperAdapter : RecyclerView.Adapter<PopularWallpaperViewHolder>() {

    var popularWallpaperModel = mutableListOf<PopularWallpaperModel>()

    fun setMovieList(featureds: List<PopularWallpaperModel>) {
        this.popularWallpaperModel = featureds.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularWallpaperViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterPopularWallpaperBinding.inflate(inflater, parent, false)
        return PopularWallpaperViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularWallpaperViewHolder, position: Int) {
        val movie = popularWallpaperModel[position]
        Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)

    }

    override fun getItemCount(): Int {
        return popularWallpaperModel.size
    }
}

class PopularWallpaperViewHolder(val binding: AdapterPopularWallpaperBinding) : RecyclerView.ViewHolder(binding.root) {

}