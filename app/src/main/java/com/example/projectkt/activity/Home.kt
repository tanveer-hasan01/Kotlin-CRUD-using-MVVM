package com.example.projectkt.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectkt.R


class Home : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.hide()


        /*window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )*/


      /*  Handler().postDelayed({
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000 is the delayed time in milliseconds.*/
    }




    }
