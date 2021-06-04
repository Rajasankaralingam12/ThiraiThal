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
import com.thiraithal.ui.base.BaseTestFragment
import kotlinx.android.synthetic.main.fragment_base.view.*
import kotlinx.android.synthetic.main.fragment_cataegories.*
import kotlinx.android.synthetic.main.fragment_cataegories.view.*
import kotlinx.android.synthetic.main.fragment_test_base.view.*

class CategoriesFragment : BaseTestFragment(){

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
        categoriesBinding = FragmentCataegoriesBinding.inflate(layoutInflater)
        mainViewBinding.root.llview.addView(FragmentCataegoriesBinding.inflate(layoutInflater).root)
        showSearchView(false)

    }

}