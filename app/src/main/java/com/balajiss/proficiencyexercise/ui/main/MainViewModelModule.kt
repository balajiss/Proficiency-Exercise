package com.balajiss.proficiencyexercise.ui.main

import androidx.lifecycle.ViewModel
import com.balajiss.proficiencyexercise.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel
}