package com.balajiss.proficiencyexercise.ui.main

import com.balajiss.proficiencyexercise.data.main.NetworkService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Provides
    fun provideMainService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Provides
    fun provideListAdapter() = ListAdapter()
}