package com.gunawan.eratani.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.gunawan.eratani.R
import com.gunawan.eratani.databinding.RowMasterBarangBinding
import com.gunawan.eratani.repository.local.model.BarangModel

class MasterBarangAdapter(private val listBarang: List<BarangModel>) : RecyclerView.Adapter<MasterBarangAdapter.ViewHolder>() {
    private var listener: OnCustomClickListener? = null

    interface OnCustomClickListener {
        fun onEditClicked(position: Int)
        fun onDeleteClicked(position: Int)
    }

    fun setOnCustomClickListener(listener: OnCustomClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowMasterBarangBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return listBarang.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
        holder.binding.tvName.text      = listBarang[position].name
        holder.binding.tvStock.text     = "Stok : " + listBarang[position].stock
        holder.binding.ivOptionMenu.setOnClickListener {
            val popupMenu = PopupMenu(holder.itemView.context, holder.binding.ivOptionMenu)
            popupMenu.menuInflater.inflate(R.menu.option_menu_barang,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_edit ->
                        listener?.onEditClicked(position)
                    R.id.action_delete ->
                        listener?.onDeleteClicked(position)
                }
                true
            }
            popupMenu.show()
        }
    }

    class ViewHolder(val binding: RowMasterBarangBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {}
    }

}