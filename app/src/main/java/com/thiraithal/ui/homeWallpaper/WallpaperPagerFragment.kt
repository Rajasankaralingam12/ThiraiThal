package com.thiraithal.ui.homeWallpaper

import android.view.View
import androidx.fragment.app.Fragment
import com.thiraithal.ui.base.BasePagerFragment
import com.thiraithal.ui.homeWallpaper.categories.CategoriesFragment
import com.thiraithal.ui.homeWallpaper.premium.PremiumFragment
import com.thiraithal.ui.homeWallpaper.wallpapers.WallpapersFragment
import com.thiraithal.ui.videowallpapers.VideoWallpapersFragment

class WallpaperPagerFragment : BasePagerFragment()
{
    override fun addTabFragments(view: View) {
        val tabTitles = arrayOf("Home", "Categories", "Premium")
        val fragments : ArrayList<Fragment> = ArrayList()
        fragments.add(WallpapersFragment())
        fragments.add(CategoriesFragment())
        fragments.add(PremiumFragment())
        setTabFragment(fragments, tabTitles,0)
    }
}