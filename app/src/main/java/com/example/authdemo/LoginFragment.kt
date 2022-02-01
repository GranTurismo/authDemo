package com.example.authdemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.commit
import com.example.authdemo.databinding.FragmentLoginPageBinding
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class LoginFragment : BaseFragment<FragmentLoginPageBinding>(R.layout.fragment_login_page)
{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        if (auth.currentUser != null)
            navigateToDashboard()

        with(binding!!) {
            submit.setOnClickListener {
                if (validation(emailField, passwordField))
                {
                    val email = emailField.text.toString()
                    val pass = passwordField.text.toString()
                    auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful)
                            navigateToDashboard()
                        else
                            showToast(R.string.incorrect_credentials)
                    }
                } else
                    showToast(R.string.short_data)
            }
            register.setOnClickListener {
                auth.createUserWithEmailAndPassword(
                        emailField.text.toString(),
                        passwordField.text.toString()
                )
                        .addOnCompleteListener {
                            if (it.isSuccessful)
                                showToast(R.string.reg_complete)
                            else
                                showToast(R.string.reg_failed)
                        }
            }
        }
    }

    private fun showToast(text: String) =
            Toast.makeText(view!!.context, text, Toast.LENGTH_LONG).show()

    private fun showToast(text: Int) = showToast(getString(text))

    private fun navigateToDashboard()
    {
        parentFragmentManager.commit {
            replace((binding!!.root.parent as View).id, DashboardFragment())
            setReorderingAllowed(true)
        }
    }

    private fun validation(email: EditText, pass: EditText) =
            !(email.text.length < 8 || pass.text.length < 6)

    override fun getFragmentBinding(view: View) = FragmentLoginPageBinding.bind(view)
}