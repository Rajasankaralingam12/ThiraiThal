package com.thiraithal.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.thiraithal.R


abstract class BaseFragment : Fragment(){

    protected abstract fun getViewBinding(): ViewBinding

    protected abstract fun getViewModel()

    protected abstract fun onFragmentCreated(view: View)

    protected abstract fun onParentFragmentCreated(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,  binding: ViewBinding)
    protected lateinit var linLayFragment :LinearLayout
    protected lateinit var searchView :LinearLayout
    protected lateinit var   mainView : View
    protected lateinit var binding: ViewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mainView = inflater.inflate(R.layout.fragment_base, container, false)
       initView(inflater, container, savedInstanceState)
        binding = getViewBinding()
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentCreated(view)

    }

    protected fun showSearchView(isShow: Boolean){
        if(isShow){
            searchView.visibility = View.VISIBLE;
        }else{
            searchView.visibility = View.GONE;
        }

    }

    private fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        linLayFragment = mainView.findViewById(R.id.linLayFragment)
        searchView = mainView.findViewById(R.id.searchView)
        binding = getViewBinding()
        onParentFragmentCreated(inflater, container, savedInstanceState, binding)
    }

}