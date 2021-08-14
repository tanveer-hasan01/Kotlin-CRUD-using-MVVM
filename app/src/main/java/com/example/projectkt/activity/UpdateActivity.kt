package com.example.projectkt.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.projectkt.ModelData.User
import com.example.projectkt.ViewModel.UpdateViewModel
import com.example.projectkt.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    lateinit var viewModel: UpdateViewModel
    private lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        title="Edit User"


        val user_id = intent.getStringExtra("user_id")

        initViewModel()

        updateObservable()



        if(user_id != null) {
            loadUserData(user_id)
        }


        binding.btUpdate.setOnClickListener {

            val user = User("", binding.etName.text.toString(), binding.etEmail.text.toString(), "Active", "Male")
            viewModel.updateUser(intent.getStringExtra("user_id").toString(), user)

        }


    }

   private fun initViewModel() {

           viewModel = ViewModelProvider(this).get(UpdateViewModel::class.java)

    }

    private fun loadUserData(user_id: String?) {
        viewModel.getLoadUserObservable().observe(this, androidx.lifecycle.Observer {

            if (it != null){

                binding.etName.setText(it.data?.name)
                binding.etEmail.setText(it.data?.email)
            }

        })
        viewModel.getUserData(user_id)
    }


    private fun updateObservable() {
        viewModel.getUpdateObservable().observe(this, androidx.lifecycle.Observer {

            if(it == null) {
                Toast.makeText(this, "Failed to create/update new user...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Successfully updated user...", Toast.LENGTH_LONG).show()
                val intent = Intent(this, ListActivity::class.java);
                startActivity(intent);
            }


        })
    }


}