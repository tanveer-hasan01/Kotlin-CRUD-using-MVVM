package com.example.projectkt

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
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




        initSpinner()


        binding.btLogin.setOnClickListener(View.OnClickListener {

            when {
                binding.etPhone.text.toString().isEmpty() -> {
                    binding.etPhone.error="Phone required"
                }

                else -> {

                    val intent = Intent(this, ListActivity::class.java);
                   // intent.putExtra("name", binding.etName.text.toString())
                    startActivity(intent);
                }
            }
        })

      /*  binding.etPassword.addTextChangedListener(object : TextWatcher {
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
        })*/

    }

    fun initSpinner()
    {
        val languages = resources.getStringArray(R.array.status)

        // access the spinner

        if (binding.spinnerStatus != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, languages)
            binding.spinnerStatus.adapter = adapter

            binding.spinnerStatus.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                   /* Toast.makeText(this@MainActivity,
                        binding.spinnerStatus.selectedItem.toString() + " " +
                                "" + languages[position], Toast.LENGTH_SHORT).show()*/
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

}