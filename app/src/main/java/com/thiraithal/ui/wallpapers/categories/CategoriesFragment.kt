package com.thiraithal.ui.wallpapers.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.thiraithal.databinding.FragmentCataegoriesBinding
import com.thiraithal.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_test_base.view.*

class CategoriesFragment : BaseFragment(){

    protected lateinit var categoriesBinding: ViewBinding

    fun newInstance( title: String?): CategoriesFragment {
        val categoriesFragment =
            CategoriesFragment()
        return categoriesFragment
    }

/*
    override fun getViewBinding(): ViewBinding {
        return  FragmentCataegoriesBinding.inflate(layoutInflater)
    }
*/


    override fun onFragmentCreated(view: View) {

    }



    override fun onParentFragmentCreated(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        binding: ViewBinding
    ) {
        categoriesBinding = FragmentCataegoriesBinding.inflate(layoutInflater)
        mainViewBinding.root.llview.addView(FragmentCataegoriesBinding.inflate(layoutInflater).root)
        showSearchView(false)

    }

}