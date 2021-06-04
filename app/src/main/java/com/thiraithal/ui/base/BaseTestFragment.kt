package com.thiraithal.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.thiraithal.R
import com.thiraithal.databinding.FragmentCataegoriesBinding
import com.thiraithal.databinding.FragmentTestBaseBinding
import kotlinx.android.synthetic.main.fragment_test_base.view.*

abstract class BaseTestFragment : Fragment() {

//    protected abstract fun getViewBinding(): ViewBinding

    protected abstract fun getViewModel()

    protected abstract fun onFragmentCreated(view: View)

    protected abstract fun onParentFragmentCreated(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        binding: ViewBinding
    )

    protected lateinit var linLayFragment: LinearLayout
    protected lateinit var searchView: LinearLayout
    protected lateinit var mainView: View
    protected lateinit var binding: ViewBinding
    protected lateinit var mainViewBinding: ViewBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      //  mainView = inflater.inflate(R.layout.fragment_base, container, false)

        mainViewBinding = FragmentTestBaseBinding.inflate(layoutInflater)

        initView(inflater, container, savedInstanceState)
      //  binding  = getViewBinding()
      //  mainViewBinding = binding
        return mainViewBinding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentCreated(view)

    }

    protected fun showSearchView(isShow: Boolean) {
        if (isShow) {
            mainViewBinding.root.search_bar.visibility = View.VISIBLE;
        } else {
            mainViewBinding.root.search_bar.visibility = View.GONE;
        }

    }

    private fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        onParentFragmentCreated(inflater, container, savedInstanceState, mainViewBinding)
    }

}