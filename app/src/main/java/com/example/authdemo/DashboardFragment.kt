package com.example.authdemo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.authdemo.databinding.DashboardFragmentLayoutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DashboardFragment: Fragment(R.layout.dashboard_fragment_layout)
{
    private lateinit var binding:DashboardFragmentLayoutBinding
    private var auth: FirebaseAuth?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        binding = DashboardFragmentLayoutBinding.bind(view)
        auth = Firebase.auth

        binding.userEmailLabel.text = "${getString(R.string.emailLabel)} ${auth!!.currentUser!!.email}";
        binding.logOut.setOnClickListener {
            auth!!.signOut()
            navigateToLoginPage()
        }
    }

    private fun navigateToLoginPage()=parentFragmentManager
            .beginTransaction()
            .setReorderingAllowed(true)
            .replace((binding.root.parent as View).id,login_page())
            .commit()
}