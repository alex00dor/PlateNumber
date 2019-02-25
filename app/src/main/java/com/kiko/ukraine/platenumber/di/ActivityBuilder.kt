package com.kiko.ukraine.platenumber.di

import com.kiko.ukraine.platenumber.di.module.PlateInfoModule
import com.kiko.ukraine.platenumber.di.scope.PerActivity
import com.kiko.ukraine.platenumber.presentation.main.ui.MainActivity
import com.kiko.ukraine.platenumber.presentation.plateinfo.ui.PlateInfoActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
internal abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [PlateInfoModule::class])
    internal abstract fun bindPlateInfoActivity(): PlateInfoActivity

}