package com.balajiss.proficiencyexercise.di

import com.balajiss.proficiencyexercise.ProficiencyApplication
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
        ActivityModule::class
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