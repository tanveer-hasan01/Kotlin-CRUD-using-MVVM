package com.example.projectkt

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.projectkt.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.btLogin.setOnClickListener(View.OnClickListener {

            when {
                binding.etPhone.text.toString().isEmpty() -> {
                    binding.etPhone.error="Phone required"

                }
                binding.etPassword.text.toString().isEmpty() -> {
                    binding.etPassword.error="Password required"
                }
                else -> {

                    val intent = Intent(this, Home::class.java);
                    intent.putExtra("name", binding.etName.text.toString())
                    startActivity(intent);


                }
            }


        })

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if (p0.toString().length<8)
                {
                    binding.etPassword.error="Week password"
                }else if (p0.toString().isEmpty())
                {
                    binding.etPassword.error = "Password required"
                }


            }
        })





    }
}