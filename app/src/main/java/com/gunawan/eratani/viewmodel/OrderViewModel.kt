package com.gunawan.eratani.viewmodel

import androidx.lifecycle.ViewModel
import com.gunawan.eratani.repository.OrderRepository
import com.gunawan.eratani.repository.local.model.OrderModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(private val repo: OrderRepository) : ViewModel() {

    fun insertOrder(order: OrderModel) = runBlocking {
        this.launch(Dispatchers.IO) {
            repo.insertOrder(order)
        }
    }

}