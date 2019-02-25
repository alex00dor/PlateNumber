package com.kiko.ukraine.platenumber.di.module

import com.kiko.ukraine.platenumber.datasource.local.LocalDataSource
import com.kiko.ukraine.platenumber.datasource.local.model.PlateInfoDao
import com.kiko.ukraine.platenumber.datasource.network.ApiFakeRepository
import com.kiko.ukraine.platenumber.domain.repository.LocalRepository
import com.kiko.ukraine.platenumber.domain.repository.PlateInfoRepository
import com.kiko.ukraine.platenumber.presentation.plateinfo.ui.PlateInfoActivity
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Module
    companion object {
        @Provides @Singleton @JvmStatic
        fun providePlateInfoRepository() : PlateInfoRepository = ApiFakeRepository

        @Provides @Singleton @JvmStatic
        fun provideLocalRepository(dao : PlateInfoDao) : LocalRepository = LocalDataSource(dao)
    }
}