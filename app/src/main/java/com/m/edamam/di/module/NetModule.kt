package com.m.edamam.di.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.m.edamam.constants.*
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())

        okHttpClient.interceptors().add(Interceptor { chain ->
            var request = chain.request()
            val url = request.url().newBuilder()
                    .addQueryParameter(QUERY_PARAM_APP_ID, APP_ID)
                    .addQueryParameter(QUERY_PARAM_APP_KEY, APP_KEY)
                    .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        })

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideBaseUrl(): String = RETROFIT_BASE_URL

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory =
            GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideCoroutineCallAdapterFactory(): CoroutineCallAdapterFactory =
            CoroutineCallAdapterFactory()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient,
                        converterFactory: GsonConverterFactory,
                        callAdapterFactory: CoroutineCallAdapterFactory,
                        baseUrl: String): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(converterFactory)
                    .addCallAdapterFactory(callAdapterFactory)
                    .build()

}
