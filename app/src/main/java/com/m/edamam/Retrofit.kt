package com.m.edamam

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.m.edamam.constants.*
import com.m.edamam.data.repositories.EdamamApi
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
                    .addQueryParameter(QUERY_PARAM_APP_ID, APP_ID)
                    .addQueryParameter(QUERY_PARAM_APP_KEY, APP_KEY)
                    .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        })

        val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(RETROFIT_BASE_URL)
                .client(okHttpClient.build())
                .build()

        var edamamService : EdamamApi = retrofit.create(EdamamApi::class.java)
        return edamamService
    }
}
