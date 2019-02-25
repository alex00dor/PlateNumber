package com.kiko.ukraine.platenumber.di.component

import android.app.Application
import com.kiko.ukraine.platenumber.di.ActivityBuilder
import com.kiko.ukraine.platenumber.di.module.*

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelModule::class,
        ActivityBuilder::class,
        DatabaseModule::class,
        RepositoryModule::class,
        UseCaseModule::class
    ]
)
internal interface AppComponent : AndroidInjector<DaggerApplication> {

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}