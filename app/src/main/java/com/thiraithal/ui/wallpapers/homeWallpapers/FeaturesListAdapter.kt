package com.thiraithal.ui.wallpapers.homeWallpapers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thiraithal.databinding.AdapterWallpaperBinding
import com.thiraithal.model.FeaturedModel


class WallpaperAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var movies = mutableListOf<FeaturedModel>()

    fun setMovieList(featureds: List<FeaturedModel>) {
        this.movies = featureds.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterWallpaperBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.name.text = movie.name
        Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)

    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

class MainViewHolder(val binding: AdapterWallpaperBinding) : RecyclerView.ViewHolder(binding.root) {

}