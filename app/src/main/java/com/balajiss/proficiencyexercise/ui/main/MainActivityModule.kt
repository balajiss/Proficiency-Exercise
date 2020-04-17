package com.balajiss.proficiencyexercise.ui.main

import com.balajiss.proficiencyexercise.di.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindMainFragment(): ListFragment
}