package com.gunawan.eratani.repository.local.model

data class BarangOrderModel(
    var idBarang: Int? = null,
    var name: String,
    var stock: Int,
    var totalOrder: Int
)