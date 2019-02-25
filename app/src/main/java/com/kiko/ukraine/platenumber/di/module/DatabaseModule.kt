package com.kiko.ukraine.platenumber.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.kiko.ukraine.platenumber.datasource.local.LocalDatabase
import com.kiko.ukraine.platenumber.datasource.local.model.PlateInfoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DatabaseModule{

    @Module
    companion object {
        @Provides @Singleton @JvmStatic
        fun providePlateInfoDao(database: LocalDatabase) : PlateInfoDao = database.plateInfoDao()

        @Provides @Singleton @JvmStatic
        fun provideLocalDatabase(context: Context) : LocalDatabase
                = Room.databaseBuilder(context, LocalDatabase::class.java, "database").build()
    }
}