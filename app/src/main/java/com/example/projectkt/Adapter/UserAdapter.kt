package com.example.projectkt.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkt.ModelData.User
import com.example.projectkt.R
import kotlinx.android.synthetic.main.item_recycler_view.view.*
import okhttp3.internal.immutableListOf

class UserAdapter:RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    var userList= immutableListOf<User>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.MyViewHolder {
        val inflater =LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view,parent,false)

        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {




    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewName = view.textViewName
        val textViewStatus = view.tt_status
        val textViewGender = view.tt_gender

        fun bind(data : User) {
            textViewName.text = data.name
            textViewGender.text = data.gender
            textViewStatus.text = data.status
        }
    }


   /* class MyViewHolder(view: View):RecyclerView.ViewHolder(view){

        val textViewName = view.textViewName

        fun bind(data : User)
        {

        }

    }*/




}