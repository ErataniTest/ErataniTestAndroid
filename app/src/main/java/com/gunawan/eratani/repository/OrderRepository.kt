package com.gunawan.eratani.repository

import androidx.lifecycle.LiveData
import com.gunawan.eratani.repository.local.ErataniDAO
import com.gunawan.eratani.repository.local.model.BarangModel
import com.gunawan.eratani.repository.local.model.OrderModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRepository @Inject constructor(private val erataniDAO: ErataniDAO) {

    suspend fun insertOrder(order: OrderModel) = erataniDAO.insertOrder(order)

}