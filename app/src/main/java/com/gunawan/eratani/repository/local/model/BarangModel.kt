package com.gunawan.eratani.repository.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barang")
data class BarangModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_barang")
    var idBarang: Int? = null,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "stock")
    var stock: Int
)