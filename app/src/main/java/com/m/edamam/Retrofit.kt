package com.m.edamam

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.m.edamam.constants.APP_ID
import com.m.edamam.constants.APP_KEY
import com.m.edamam.repositories.EdamamApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit private constructor() {

    companion object {
        val instance : Retrofit by lazy {
            Holder.INSTANCE
        }
    }

    private object Holder{
        val INSTANCE = Retrofit()
    }

    fun getEdamamService() : EdamamApi{
        val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())

        okHttpClient.interceptors().add(Interceptor { chain ->
            var request = chain.request()
            val url = request.url().newBuilder()
                    .addQueryParameter("app_id", APP_ID)
                    .addQueryParameter("app_key", APP_KEY)
                    .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        })

        val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.edamam.com/")
                .client(okHttpClient.build())
                .build()

        var edamamService : EdamamApi = retrofit.create(EdamamApi::class.java)
        return edamamService
    }
}