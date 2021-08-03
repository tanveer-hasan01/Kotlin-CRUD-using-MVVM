package com.example.projectkt.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkt.ModelData.User
import com.example.projectkt.R
import kotlinx.android.synthetic.main.item_recycler_view.view.*
import kotlin.coroutines.coroutineContext


class UserAdapter:RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    var userList= mutableListOf<User>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.MyViewHolder {
        val inflater =LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view,parent,false)
        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(userList[position])



    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewName = view.tt_name
        val textViewStatus = view.tt_status
        val textViewGender = view.tt_gender
        val imStatus=view.im_status
        val imgender=view.im_gender



        fun bind(data : User) {

            val getName=data.name
            val getGender=data.gender
            val getStatus=data.status

            textViewName.text = "  "+getName
            textViewGender.text = "  "+getGender
            textViewStatus.text = "  "+getStatus


            if (data.gender.equals("male"))
            {
                imgender.setImageResource (R.drawable.ic_male )
            }
            else{
                imgender.setImageResource (R.drawable.ic_female )
            }


            if (data.status.equals("active"))
            {
                imStatus.setImageResource (R.drawable.ic_active )
            }
            else{
                imStatus.setImageResource (R.drawable.ic_disable )
            }



        }
    }


   /* class MyViewHolder(view: View):RecyclerView.ViewHolder(view){

        val textViewName = view.textViewName

        fun bind(data : User)
        {

        }

    }*/




}