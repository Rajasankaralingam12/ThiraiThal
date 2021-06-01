package com.thiraithal.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.thiraithal.R

abstract class BasePagerFragment : Fragment() {

    abstract fun addTabFragments(view: View)

    private lateinit var viewPager: ViewPager
    private lateinit var tabs : TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_pager_base, container, false)
        viewPager = view.findViewById(R.id.viewPager)
        tabs  = view.findViewById(R.id.tabs)
        addTabFragments(view)
        return view
    }

    protected fun setTabFragment(fragments : ArrayList<Fragment>, tabTitles : Array<String>, currentFragment : Int){
        val adapter = FragmentPagerAdapter(childFragmentManager, tabTitles, fragments)
        viewPager.offscreenPageLimit = fragments.size-1
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
        viewPager.currentItem = currentFragment
}
}

