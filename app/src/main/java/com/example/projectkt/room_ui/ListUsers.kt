package com.example.projectkt.room_ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectkt.AddUserActivity
import com.example.projectkt.R
import com.example.projectkt.databinding.ActivityAddBinding
import com.example.projectkt.databinding.ActivityListUsersBinding
import com.example.projectkt.room.RoomAdapter
import com.example.projectkt.room.UserViewModel

class ListUsers : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: ActivityListUsersBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUsersBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        title= "Offline DB"

        val adapter = RoomAdapter()
        binding.userRecycler.adapter=adapter
        binding.userRecycler.layoutManager=LinearLayoutManager(this)
        userViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.readAllData.observe(this, Observer {user ->

            adapter.setData(user)

        })


        binding.floatingActionButton.setOnClickListener {

            val intent = Intent(this, AddActivity::class.java);
            startActivity(intent);
        }

    }
}