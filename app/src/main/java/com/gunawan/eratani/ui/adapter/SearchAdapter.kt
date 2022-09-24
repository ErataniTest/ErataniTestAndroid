package com.gunawan.eratani.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gunawan.eratani.databinding.RowSearchBinding

class SearchAdapter(private val listText: MutableList<String>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return listText.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
        holder.binding.tvText.text = listText[position]
    }

    class ViewHolder(val binding: RowSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {}
    }

}