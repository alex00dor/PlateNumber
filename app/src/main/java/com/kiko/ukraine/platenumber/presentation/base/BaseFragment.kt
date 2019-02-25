package com.kiko.ukraine.platenumber.presentation.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment

abstract class BaseFragment: DaggerFragment(){
    protected inline fun <reified T : ViewModel> getViewModel(
        viewModelFactory: ViewModelProvider.Factory
    ): T =
        ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}