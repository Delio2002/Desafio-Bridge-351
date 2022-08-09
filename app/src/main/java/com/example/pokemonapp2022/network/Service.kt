package com.example.pokemonapp2022.network

import com.example.pokemonapp2022.network.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Service {

    fun <API> create(apiClass: Class<API>): API {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(apiClass)
    }
}