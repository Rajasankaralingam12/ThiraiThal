package com.thiraithal.ui.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentPagerAdapter(fm: FragmentManager, val tabTitles: Array<String>, var fragments: ArrayList<Fragment> ) : FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return fragments.get(position);

    }
    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun getCount(): Int {
        return fragments.size;
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return  tabTitles.get(position)
    }

}