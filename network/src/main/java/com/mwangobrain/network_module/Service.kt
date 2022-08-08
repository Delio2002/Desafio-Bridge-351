package com.deliodev.network

import com.google.gson.GsonBuilder
import com.mwangobrain.network.HashGenerate
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val API_PUBLIC="4dca613e1fb17f606801b604fe4c833b"
const val API_PRIVATE="93b89f393de8af4f9ab7f8f34988500153cf7944"
private const val BASE_URL = "https://gateway.marvel.com/"
val TIME_STAMP = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())

class Service {

    val logging = HttpLoggingInterceptor()
    val httpClient = OkHttpClient.Builder()

    fun logInterceptor() =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    fun <API> createService(apiClass: Class<API>): API {
        logging.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", API_PUBLIC)
                .addQueryParameter("ts", TIME_STAMP.toString())
                .addQueryParameter("hash", HashGenerate.generate(TIME_STAMP, API_PRIVATE, API_PUBLIC))
                .build()

            chain.proceed(original.newBuilder().url(url).build())
        }
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.addInterceptor(logInterceptor()).build())
            .build()


        return retrofit.create(apiClass)
    }



}