package com.gunawan.eratani.repository

import androidx.lifecycle.LiveData
import com.gunawan.eratani.repository.local.ErataniDAO
import com.gunawan.eratani.repository.local.model.BarangModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BarangRepository @Inject constructor(private val erataniDAO: ErataniDAO) {

    suspend fun insertBarang(barang: BarangModel) = erataniDAO.insertBarang(barang)

    suspend fun updateBarang(barang: BarangModel) = erataniDAO.updateBarang(barang)

    suspend fun deleteBarang(barang: BarangModel) = erataniDAO.deleteBarang(barang)

    fun getAllBarang(): LiveData<List<BarangModel>> = erataniDAO.getAllBarang()

}