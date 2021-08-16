package com.example.projectkt.activity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectkt.Adapter.UserAdapter
import com.example.projectkt.ModelData.ModelUser
import com.example.projectkt.ModelData.User
import com.example.projectkt.ModelData.UserResponse
import com.example.projectkt.R
import com.example.projectkt.ViewModel.ListViewModel
import com.example.projectkt.databinding.ActivityListBinding
import com.example.projectkt.room_ui.ListUsers
import com.google.android.material.navigation.NavigationView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity(), UserAdapter.OnItemLongClickListener{

    lateinit var toogle: ActionBarDrawerToggle
    private lateinit var binding: ActivityListBinding
    lateinit var Useradapter: UserAdapter
    lateinit var viewModel:ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val drawerLayout:DrawerLayout=findViewById(R.id.drawer_layout)
        val nevView:NavigationView=findViewById(R.id.nev_view)
        toogle= ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nevView.setNavigationItemSelectedListener {
            when (it.itemId){

                R.id.nevHome ->Toast.makeText(this,"Message Clicked",Toast.LENGTH_LONG).show()

                R.id.nev_room ->{

                    val intent = Intent(this, ListUsers::class.java);
                    startActivity(intent);
                }


                R.id.nevmessage ->Toast.makeText(this,"Message Clicked",Toast.LENGTH_LONG).show()
                R.id.nev_setting ->Toast.makeText(this,"Setting Clicked",Toast.LENGTH_LONG).show()
                R.id.nev_sync ->Toast.makeText(this,"Sync Clicked",Toast.LENGTH_LONG).show()
                R.id.share ->Toast.makeText(this,"Share Clicked",Toast.LENGTH_LONG).show()
            }

            true
        }



        binding.btAdd.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, AddUserActivity::class.java);
            startActivity(intent);

        })

        initRecyclerView()
        initViewModel()
        searchUser()


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toogle.onOptionsItemSelected(item))
        {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    private fun deleteUser(user_id: String?) {

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        viewModel.getDeleteUserObservable().observe(this, Observer <UserResponse?>{
            if(it == null) {
                Toast.makeText(this, "Failed to delete user...", Toast.LENGTH_LONG).show()
            } else {

                Toast.makeText(this, "Successfully deleted user...", Toast.LENGTH_LONG).show()

            }
        })
        viewModel.deleteUser(user_id)
    }

    private fun searchUser() {
        bt_search.setOnClickListener {
            if(!TextUtils.isEmpty(et_search.text.toString())) {
                viewModel.searchUser(et_search.text.toString())
            } else {
                viewModel.getUsersList()
            }
        }
    }

    private fun initRecyclerView() {

        recyclerView.apply {

            layoutManager = LinearLayoutManager(this@ListActivity)
            val decoration = DividerItemDecoration(this@ListActivity, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            Useradapter = UserAdapter(this@ListActivity)
            adapter = Useradapter

        }
    }

    fun initViewModel() {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        viewModel.getUserListObserveable().observe(this, Observer<ModelUser> {

            if (it==null)
            {
                Toast.makeText(this,"No result found !",Toast.LENGTH_LONG).show()

            }else{
                Useradapter.userList.addAll(it.data)
                Useradapter.notifyDataSetChanged()

            }
        })
        viewModel.getUsersList()
    }





    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == 1000) {
            viewModel.getUsersList()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }



    override fun onItemEditCLick(user: User) {

        var builder= AlertDialog.Builder(this)
        builder.setTitle("Confirmation")
        builder.setIcon(R.drawable.ic_check)
        builder.setMessage("Are you sure to delete this item ?")

        builder.setPositiveButton("Delete") { dialog, which ->
            deleteUser(user.id)


        }

        builder.setNegativeButton("Cancel") { dialog, which ->
        }
        builder.show()


    }




}