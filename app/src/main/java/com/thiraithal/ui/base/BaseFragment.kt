package com.thiraithal.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.thiraithal.databinding.FragmentBaseBinding
import kotlinx.android.synthetic.main.fragment_base.view.*

abstract class BaseFragment : Fragment() {

    protected abstract fun onFragmentCreated(view: View)

    protected abstract fun onParentFragmentCreated(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        binding: ViewBinding
    )

    protected lateinit var mainViewBinding: ViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        mainViewBinding = FragmentBaseBinding.inflate(layoutInflater)

        initView(inflater, container, savedInstanceState)
        return mainViewBinding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentCreated(view)

    }

    protected fun showSearchView(isShow: Boolean) {
        if (isShow) {
            mainViewBinding.root.searchView.visibility = View.VISIBLE;
        } else {
            mainViewBinding.root.searchView.visibility = View.GONE;
        }

    }

    protected fun linearLayoutVisibility(showFragmentLayout: Boolean){

        if(showFragmentLayout){

            mainViewBinding.root.llview.visibility = View.VISIBLE
        }else{
            mainViewBinding.root.llview.visibility = View.GONE
        }
    }


    protected fun progressBarVisibility(showProgressBar: Boolean){

        if(showProgressBar){

            mainViewBinding.root.pbFragment.visibility = View.VISIBLE
            activity?.getWindow()?.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }else{
            mainViewBinding.root.pbFragment.visibility = View.GONE
            activity?.getWindow()?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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