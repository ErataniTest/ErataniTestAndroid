package com.gunawan.eratani.repository.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gunawan.eratani.repository.local.model.BarangModel
import com.gunawan.eratani.repository.local.model.OrderModel

@Dao
interface ErataniDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBarang(barang: BarangModel)

    @Update
    suspend fun updateBarang(contact: BarangModel)

    @Delete
    suspend fun deleteBarang(contact: BarangModel)

    @Query("SELECT * FROM barang")
    fun getAllBarang() : LiveData<List<BarangModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderModel)

}