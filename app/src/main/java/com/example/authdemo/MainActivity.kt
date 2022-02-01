package com.example.authdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.authdemo.databinding.ActivityMainBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class MainActivity : AppCompatActivity()
{
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container,login_page())
                .commit()
    }
}