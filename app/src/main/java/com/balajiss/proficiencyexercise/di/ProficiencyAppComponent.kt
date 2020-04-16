package com.balajiss.proficiencyexercise.di

import com.balajiss.proficiencyexercise.ProficiencyApplication
import com.balajiss.proficiencyexercise.network.RetrofitModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppContextModule::class,
        ActivityModule::class,
        RetrofitModule::class,
        ProficiencyAppModule::class
    ]
)
interface ProficiencyAppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: ProficiencyApplication): Builder

        fun build(): ProficiencyAppComponent
    }
}