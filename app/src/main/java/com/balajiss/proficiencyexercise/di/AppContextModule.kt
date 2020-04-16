package com.balajiss.proficiencyexercise.di

import android.content.Context
import com.balajiss.proficiencyexercise.ProficiencyApplication
import dagger.Binds
import dagger.Module

@Module
abstract class AppContextModule {

    @Binds
    abstract fun provideContext(application: ProficiencyApplication): Context
}