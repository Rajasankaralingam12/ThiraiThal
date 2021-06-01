package com.thiraithal.ui.homeWallpaper.premium

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.thiraithal.R
import com.thiraithal.databinding.FragmentCataegoriesBinding
import com.thiraithal.databinding.FragmentPremiumBinding
import com.thiraithal.ui.base.BaseFragment
import com.thiraithal.ui.homeWallpaper.wallpapers.WallpapersFragment

class PremiumFragment  : BaseFragment() {



    fun newInstance( title: String?): PremiumFragment {
        val fragmentFirst =
            PremiumFragment()
        return fragmentFirst
    }

    override fun getViewBinding(): ViewBinding {
        return  FragmentPremiumBinding.inflate(layoutInflater)
    }

    override fun getViewModel() {
    }

    override fun onFragmentCreated(view: View) {
        Toast.makeText(activity,"Wallpaper Fragment Created", Toast.LENGTH_SHORT).show()
    }

    override fun onParentFragmentCreated(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, binding: ViewBinding) {
        val view : View = inflater.inflate(R.layout.fragment_premium, container,false)
        linLayFragment.addView(view)
        showSearchView(false)

    }


}