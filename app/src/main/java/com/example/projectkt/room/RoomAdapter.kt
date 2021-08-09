package com.example.projectkt.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkt.R
import kotlinx.android.synthetic.main.item_user.view.*

class RoomAdapter: RecyclerView.Adapter<RoomAdapter.MyHolder>() {

    private var userList= emptyList<UsersModel>()


    class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val  currentItem= userList[position]
        holder.itemView.first_name.text=currentItem.firstName.toString()
        holder.itemView.last_name.text=currentItem.last_name.toString()
        holder.itemView.age.text=currentItem.age.toString()


    }

    override fun getItemCount(): Int {

        return userList.size
    }


    fun setData(user:List<UsersModel>){

        this.userList=user
        notifyDataSetChanged()

    }
}