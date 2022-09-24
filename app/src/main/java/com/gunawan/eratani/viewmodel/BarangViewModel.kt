package com.gunawan.eratani.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gunawan.eratani.repository.BarangRepository
import com.gunawan.eratani.repository.local.model.BarangModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class BarangViewModel @Inject constructor(private val repo: BarangRepository) : ViewModel() {
    var ldGetAllBarang: LiveData<List<BarangModel>>? = null

    fun insertBarang(barang: BarangModel) = runBlocking {
        this.launch(Dispatchers.IO) {
            repo.insertBarang(barang)
        }
    }

    fun updateBarang(barang: BarangModel) = runBlocking {
        this.launch(Dispatchers.IO) {
            repo.updateBarang(barang)
        }
    }

    fun deleteBarang(barang: BarangModel) = runBlocking {
        this.launch(Dispatchers.IO) {
            repo.deleteBarang(barang)
        }
    }

    fun getAllBarang(): LiveData<List<BarangModel>>? {
        ldGetAllBarang = repo.getAllBarang()

        return ldGetAllBarang
    }

}