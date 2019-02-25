package com.kiko.ukraine.platenumber.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kiko.ukraine.platenumber.di.key.ViewModelKey
import com.kiko.ukraine.platenumber.presentation.base.ViewModelFactory
import com.kiko.ukraine.platenumber.presentation.main.MainViewModel
import com.kiko.ukraine.platenumber.presentation.plateinfo.PlateInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlateInfoViewModel::class)
    abstract fun bindPlateInfoViewModel(plateInfoViewModel: PlateInfoViewModel) : ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}