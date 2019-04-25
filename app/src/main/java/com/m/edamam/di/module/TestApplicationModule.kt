package com.m.edamam.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class TestApplicationModule(context: Context) : AppModule(context) {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("http://localhost:8080/")
                    .build()

}