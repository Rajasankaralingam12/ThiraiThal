package com.thiraithal.ui.homeWallpaper.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.thiraithal.R
import com.thiraithal.databinding.FragmentCataegoriesBinding
import com.thiraithal.databinding.FragmentWallpapersBinding
import com.thiraithal.ui.base.BaseFragment

class CategoriesFragment : BaseFragment(){

    fun newInstance( title: String?): CategoriesFragment {
        val categoriesFragment =
            CategoriesFragment()
        return categoriesFragment
    }

    override fun getViewBinding(): ViewBinding {
        return  FragmentCataegoriesBinding.inflate(layoutInflater)
    }

    override fun getViewModel() {
    }

    override fun onFragmentCreated(view: View) {
    }

    override fun onParentFragmentCreated(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        binding: ViewBinding
    ) {
        val view : View = inflater.inflate(R.layout.fragment_cataegories, container,false)
        linLayFragment.addView(view)
        showSearchView(false)
    }

}