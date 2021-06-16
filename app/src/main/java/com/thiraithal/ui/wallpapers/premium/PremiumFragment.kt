package com.thiraithal.ui.wallpapers.premium

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.thiraithal.databinding.FragmentPremiumBinding
import com.thiraithal.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_test_base.view.*

class PremiumFragment  : BaseFragment() {

   private lateinit var fragmentViewBinding: ViewBinding

    fun newInstance( title: String?): PremiumFragment {
        val fragmentFirst =
            PremiumFragment()
        return fragmentFirst
    }

    override fun onFragmentCreated(view: View) {
    }

    override fun onParentFragmentCreated(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, binding: ViewBinding) {
        fragmentViewBinding = FragmentPremiumBinding.inflate(layoutInflater)
        mainViewBinding.root.llview.addView(fragmentViewBinding.root)
        showSearchView(true)

    }
}