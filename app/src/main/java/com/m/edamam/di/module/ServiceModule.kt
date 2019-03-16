package com.m.edamam.di.module

import com.m.edamam.repositories.EdamamApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideEdamamService(retrofit: Retrofit): EdamamApi =
            retrofit.create(EdamamApi::class.java)
}