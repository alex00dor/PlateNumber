package com.kiko.ukraine.platenumber.di.module

import com.kiko.ukraine.platenumber.domain.repository.LocalRepository
import com.kiko.ukraine.platenumber.domain.repository.PlateInfoRepository
import com.kiko.ukraine.platenumber.domain.usecase.PlateInfoUseCase
import com.kiko.ukraine.platenumber.domain.usecase.RecentViewsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class UseCaseModule{

    @Module
    companion object {

        @Provides @Singleton @JvmStatic
        fun providePlateInfoUseCase(repository: PlateInfoRepository) : PlateInfoUseCase = PlateInfoUseCase(repository)

        @Provides @Singleton @JvmStatic
        fun provideRecentViewsUseCase(repository: LocalRepository) : RecentViewsUseCase = RecentViewsUseCase(repository)

    }
}

