package com.gunawan.eratani.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.gunawan.eratani.R
import com.gunawan.eratani.databinding.RowMasterBarangBinding
import com.gunawan.eratani.databinding.RowOrderBinding
import com.gunawan.eratani.repository.local.model.BarangOrderModel

class OrderAdapter(private val listBarangOrder: List<BarangOrderModel>) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    private var listener: OnCustomClickListener? = null

    interface OnCustomClickListener {
        fun onBtnOrderClicked(position: Int)
        fun onEditOrderClicked(position: Int)
    }

    fun setOnCustomClickListener(listener: OnCustomClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RowOrderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listBarangOrder.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
        holder.binding.tvName.text          = listBarangOrder[position].name
        holder.binding.tvStock.text         = "Stok : " + listBarangOrder[position].stock
        holder.binding.tvTotalOrder.text    = "Order : " + listBarangOrder[position].totalOrder

        if (listBarangOrder[position].totalOrder > 0) {
            holder.binding.btnAdd.visibility        = View.GONE
            holder.binding.llTotalOrder.visibility  = View.VISIBLE
        } else {
            holder.binding.btnAdd.visibility        = View.VISIBLE
            holder.binding.llTotalOrder.visibility  = View.GONE
        }

        holder.binding.btnAdd.setOnClickListener {
            listener?.onBtnOrderClicked(position)
        }

        holder.binding.llTotalOrder.setOnClickListener {
            listener?.onEditOrderClicked(position)
        }
    }

    class ViewHolder(val binding: RowOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {}
    }

}