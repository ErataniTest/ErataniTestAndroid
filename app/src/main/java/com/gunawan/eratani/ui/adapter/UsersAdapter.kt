package com.gunawan.eratani.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gunawan.eratani.databinding.RowUsersBinding
import com.gunawan.eratani.repository.remote.model.RespGetUsers


class UsersAdapter(private val listUsers: List<RespGetUsers>) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
        if (position == 0) {
            holder.binding.llHeader.visibility  = View.VISIBLE
        } else {
            holder.binding.llHeader.visibility  = View.GONE
        }
        holder.binding.tvName.text      = listUsers[position].name
        holder.binding.tvEmail.text     = listUsers[position].email
        holder.binding.tvGender.text    = listUsers[position].gender
    }

    class ViewHolder(val binding: RowUsersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {}
    }

}