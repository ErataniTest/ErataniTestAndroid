package com.gunawan.eratani.repository.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order")
data class OrderModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_order")
    var idOrder: Int? = null,

    @ColumnInfo(name = "id_barang")
    var idBarang: Int,

    @ColumnInfo(name = "qty")
    var qty: Int,

    @ColumnInfo(name = "tgl_order")
    var tglOrder: String
)