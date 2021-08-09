package com.example.projectkt.room_ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.projectkt.R
import com.example.projectkt.databinding.ActivityAddBinding
import com.example.projectkt.room.UserViewModel
import com.example.projectkt.room.UsersModel

class AddActivity : AppCompatActivity() {
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        binding = ActivityAddBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.add.setOnClickListener {

           insetDataBase()

        }

    }

    private fun insetDataBase(){

        val firstName=binding.firstName.text.toString()
        val lastName=binding.lastName.text.toString()
        val age=binding.age.text.toString()

        if (inputCheck(firstName, lastName, age)){

            val user = UsersModel(0, firstName, lastName, Integer.parseInt(age.toString()))
           mUserViewModel.addUser(user)
            Toast.makeText(this, "Successfully added!", Toast.LENGTH_LONG).show()
        }else{

            Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_LONG).show()

        }

    }

    private fun inputCheck(firstName:String,lastName: String, age: String):Boolean{

      return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


}