package com.balajiss.proficiencyexercise.di

import com.balajiss.proficiencyexercise.data.main.MainModule
import com.balajiss.proficiencyexercise.ui.main.MainActivity
import com.balajiss.proficiencyexercise.ui.main.MainViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class,
            MainModule::class,
            MainViewModelModule::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity
}