package com.gunawan.eratani.di

import android.app.Application
import androidx.room.Room
import com.gunawan.eratani.repository.local.ErataniDAO
import com.gunawan.eratani.repository.local.ErataniDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application, callback: ErataniDatabase.Callback): ErataniDatabase{
        return Room.databaseBuilder(application, ErataniDatabase::class.java, "eratanidb")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }

    @Provides
    fun provideMovieAppDao(db: ErataniDatabase): ErataniDAO {
        return db.getDErataniDAO()
    }

}