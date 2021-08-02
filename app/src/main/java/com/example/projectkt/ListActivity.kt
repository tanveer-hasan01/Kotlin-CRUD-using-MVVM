package com.example.projectkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectkt.Adapter.UserAdapter
import com.example.projectkt.ModelData.ModelUser
import com.example.projectkt.ViewModel.ListViewModel
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    lateinit var Useradapter: UserAdapter
    lateinit var viewModel:ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

      //  Toast.makeText(this,"fdsdf",Toast.LENGTH_LONG).show()


        initRecyclerView()
        initViewModel()
        searchUser()
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
            Useradapter = UserAdapter()
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
                Useradapter.userList=it.data.toMutableList()
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
}