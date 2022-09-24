package com.gunawan.eratani.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gunawan.eratani.di.ApplicationScope
import com.gunawan.eratani.repository.local.model.BarangModel
import com.gunawan.eratani.repository.local.model.OrderModel
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = arrayOf(BarangModel::class, OrderModel::class), version = 1, exportSchema = false)
abstract class ErataniDatabase : RoomDatabase() {
    abstract fun getDErataniDAO(): ErataniDAO

    class Callback @Inject constructor(
        private val database: Provider<ErataniDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback()

}