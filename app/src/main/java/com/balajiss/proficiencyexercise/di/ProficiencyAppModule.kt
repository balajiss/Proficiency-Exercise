package com.balajiss.proficiencyexercise.di

import com.balajiss.proficiencyexercise.network.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class ProficiencyAppModule {

    @Provides
    fun provideScheduler() = SchedulerProvider(Schedulers.io(), AndroidSchedulers.mainThread())
}