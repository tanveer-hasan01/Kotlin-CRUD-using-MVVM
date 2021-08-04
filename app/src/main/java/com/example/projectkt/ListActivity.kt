package com.example.projectkt

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectkt.Adapter.UserAdapter
import com.example.projectkt.ModelData.ModelUser
import com.example.projectkt.ModelData.User
import com.example.projectkt.ModelData.UserResponse
import com.example.projectkt.ViewModel.ListViewModel
import com.example.projectkt.databinding.ActivityListBinding
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity(), UserAdapter.OnItemLongClickListener{

    private lateinit var binding: ActivityListBinding
    lateinit var Useradapter: UserAdapter
    lateinit var viewModel:ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.btAdd.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, AddUserActivity::class.java);
            startActivity(intent);

        })


        initRecyclerView()
        initViewModel()
        searchUser()


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