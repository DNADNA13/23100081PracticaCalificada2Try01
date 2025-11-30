package dev.equipo.a23100081practicacalificada01.data.remote.ApiPokedex

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitPoke {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    val api: PokeApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)
    }
}
