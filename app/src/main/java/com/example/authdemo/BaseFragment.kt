package com.example.authdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment<T>(resource:Int) : Fragment(resource)
{
    protected var binding:T?=null

    protected abstract fun getFragmentBinding(view:View):T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        binding = getFragmentBinding(view)
    }
}