package com.balajiss.proficiencyexercise.di

import com.balajiss.proficiencyexercise.ui.main.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindMainFragment(): ListFragment
}