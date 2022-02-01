package com.example.authdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.authdemo.databinding.DashboardFragmentLayoutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DashboardFragment :
        BaseFragment<DashboardFragmentLayoutBinding>(R.layout.dashboard_fragment_layout)
{
    private var auth: FirebaseAuth? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        binding = DashboardFragmentLayoutBinding.bind(view)
        auth = Firebase.auth

        with(binding!!) {
            userEmailLabel.text =
                    "${getString(R.string.emailLabel)} ${auth!!.currentUser!!.email}";
            logOut.setOnClickListener {
                auth!!.signOut()
                navigateToLoginPage()
            }
        }
    }

    private fun navigateToLoginPage() =
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace((binding!!.root.parent as View).id, LoginFragment())
            }

    override fun getFragmentBinding(view: View): DashboardFragmentLayoutBinding =
            DashboardFragmentLayoutBinding.bind(view)
}