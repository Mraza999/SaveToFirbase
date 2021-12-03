package com.example.savetofirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private var userList : ArrayList<ModalUser>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var globalUserList : List<ModalUser> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_row,
        parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currenyitem = userList[position]

        holder.firstName.text = currenyitem.firstName
        holder.lastName.text = currenyitem.lastName
        holder.age.text = currenyitem.age
        holder.email.text = currenyitem.email

    }

    override fun getItemCount(): Int {

        return userList.size
    }


    fun UserList(UserList: List<ModalUser>){

        globalUserList = UserList
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {

        val firstName : TextView = itemView.findViewById(R.id.fname_et)
        val lastName  : TextView = itemView.findViewById(R.id.lname_et)
        val age : TextView = itemView.findViewById(R.id.age_et)
        val email : TextView = itemView.findViewById(R.id.email_et)


    }
}