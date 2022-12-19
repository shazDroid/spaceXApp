package com.shazdroid.spacexapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Base fragment to provide binding obj
 * @author Shahbaz Ansari
 */
abstract class BaseFragment<VB : ViewDataBinding> : Fragment(){
    protected lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, getFragmentLayout(), container, false)
        return binding.root
    }

    abstract fun getFragmentLayout(): Int
}