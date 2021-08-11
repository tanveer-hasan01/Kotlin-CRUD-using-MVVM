package com.example.projectkt.room_ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
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


        title = "Offline DB"

        if (intent.getStringExtra("first_name") == null) {


        } else {
            binding.firstName.setText(intent.getStringExtra("first_name"))
            binding.lastName.setText(intent.getStringExtra("last_name"))
            binding.age.setText(intent.getStringExtra("age"))
            binding.add.text = "Update"

        }


        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.add.setOnClickListener {

            if (intent.getStringExtra("first_name") == null)
            {
                insetDataBase()
            }else{

                updateUser()
            }


        }

    }

    private fun insetDataBase() {

        val firstName = binding.firstName.text.toString()
        val lastName = binding.lastName.text.toString()
        val age = binding.age.text.toString()

        if (inputCheck(firstName, lastName, age)) {

            val user = UsersModel(0, firstName, lastName, Integer.parseInt(age.toString()))
            mUserViewModel.addUser(user)
            Toast.makeText(this, "Successfully added!", Toast.LENGTH_LONG).show()
            val intent = Intent(this, ListUsers::class.java);
            startActivity(intent);


        } else {

            Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_LONG).show()

        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {

        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    private fun updateUser() {

        val firstName = binding.firstName.text.toString()
        val lastName = binding.lastName.text.toString()
        val age = binding.age.text.toString()
        val id = intent.getStringExtra("id").toInt()

        if (inputCheck(firstName, lastName, age)) {

            val updateUser = UsersModel(id, firstName, lastName, age.toInt())
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(this, "Update Successful", Toast.LENGTH_LONG).show()
            val intent = Intent(this, ListUsers::class.java);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Fill out all fields", Toast.LENGTH_LONG).show()

        }


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.delete_menu, menu)
        return true
    }


    override  fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_menu -> {
                Toast.makeText(applicationContext, "click on setting", Toast.LENGTH_LONG).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}