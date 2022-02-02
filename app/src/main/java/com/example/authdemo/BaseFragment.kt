package com.example.authdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

abstract class BaseFragment<T>(resource:Int) : Fragment(resource)
{
    protected var binding:T?=null
    protected lateinit var auth: FirebaseAuth

    protected abstract fun getFragmentBinding(view:View):T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        binding = getFragmentBinding(view)
        auth = Firebase.auth
    }

    protected fun showToast(text: String) =
            Toast.makeText(view!!.context, text, Toast.LENGTH_LONG).show()

    protected fun showToast(text: Int) = showToast(getString(text))
}