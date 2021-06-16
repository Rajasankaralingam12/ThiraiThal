package com.thiraithal.ui.wallpapers

import android.view.View
import androidx.fragment.app.Fragment
import com.thiraithal.ui.base.BasePagerFragment
import com.thiraithal.ui.wallpapers.categories.CategoriesFragment
import com.thiraithal.ui.wallpapers.premium.PremiumFragment
import com.thiraithal.ui.wallpapers.homeWallpapers.WallpapersFragment

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