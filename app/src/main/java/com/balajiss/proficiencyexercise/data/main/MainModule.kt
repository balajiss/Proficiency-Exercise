package com.balajiss.proficiencyexercise.data.main

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Provides
    fun provideMainService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }
}