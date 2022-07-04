package com.my.roomdb

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_user_list.view.*

class UserListAdapter(private val context:Context) : RecyclerView.Adapter<MyViewHolder>() {

    var userList = mutableListOf<Users>()

    var clickListener: ListClickListener<Users>? = null

    fun setUsers(users: List<Users>) {
        this.userList = users.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_user_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val user = userList[position]
        holder.location.text = user.location
        holder.username.text = user.userName
        holder.email.text = user.email
        holder.layout.setOnClickListener {
            clickListener?.onClick(user,position)
        }

        holder.imgDelete.setOnClickListener {
            val alertDialog = AlertDialog.Builder(context)
                .setTitle("Delete the Item")
                .setMessage("Do You Wish To Delete")
                .setPositiveButton("Ok",DialogInterface.OnClickListener{_,_ ->
                    clickListener?.onDelete(user)
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                    //pass
                })
            val b = alertDialog.create()
            b.show()
        }

    }

    fun setOnItemClick(listClickListener: ListClickListener<Users>) {
        this.clickListener = listClickListener
    }

}

class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val username = view.text_username
    val location = view.text_location
    val email = view.text_email
    val layout = view.layout
    val imgDelete = view.imgDelete
}


interface ListClickListener<T> {
    fun onClick(data: T, position: Int)
    fun onDelete(user: T)
}