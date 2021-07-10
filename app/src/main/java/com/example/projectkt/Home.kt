package com.example.projectkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectkt.databinding.ActivityHomeBinding
import com.example.projectkt.databinding.ActivityMainBinding

class Home : AppCompatActivity() {

    private lateinit var binding :ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        actionBar?.title="Home"
        binding.name.text=intent.getStringExtra("name").toString()





    }
}