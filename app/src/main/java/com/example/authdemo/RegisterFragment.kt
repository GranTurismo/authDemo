package com.example.authdemo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.commit
import com.example.authdemo.databinding.RegisterFragmentLayoutBinding

class RegisterFragment :
        BaseFragment<RegisterFragmentLayoutBinding>(R.layout.register_fragment_layout)
{
    override fun getFragmentBinding(view: View): RegisterFragmentLayoutBinding =
            RegisterFragmentLayoutBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        with(binding!!) {
            submit.setOnClickListener {
                if (isFormValid())
                {
                    auth.createUserWithEmailAndPassword(
                            emailField.text.toString(),
                            passwordField.text.toString()
                    ).addOnCompleteListener {
                        if(it.isSuccessful)
                        {
                            showToast(R.string.reg_complete)
                            parentFragmentManager.commit {
                                replace((binding!!.root.parent as View).id,DashboardFragment())
                                setReorderingAllowed(true)
                            }
                        }
                        else
                            showToast(R.string.reg_failed)
                    }
                }
                else
                    showToast(R.string.invalid_form)
            }
        }
    }

    private fun isFormValid() = with(binding!!) {
        emailField.text.length > 5
                && usernameField.text.length > 2
                && passwordField.text.length > 5
    }
}