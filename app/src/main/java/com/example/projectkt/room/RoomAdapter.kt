package com.example.projectkt.room

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkt.R
import com.example.projectkt.activity.UpdateActivity
import com.example.projectkt.room_ui.AddActivity
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

        val getFirstName =currentItem.firstName
        val getLastName =currentItem.last_name
        val age =currentItem.age.toString()

        holder.itemView.first_name.text= "First name : $getFirstName"
        holder.itemView.last_name.text="Last : $getLastName"
        holder.itemView.age.text="Age : $age"

        holder.itemView.setOnClickListener {


            val intent = Intent(it.context, AddActivity::class.java)
            intent.putExtra("first_name", currentItem.firstName)
            intent.putExtra("last_name", currentItem.last_name)
            intent.putExtra("age", currentItem.age.toString())
            intent.putExtra("id", currentItem.id.toString())


            it.context.startActivity(intent)


        }


    }

    override fun getItemCount(): Int {

        return userList.size
    }


    fun setData(user:List<UsersModel>){

        this.userList=user
        notifyDataSetChanged()

    }
}