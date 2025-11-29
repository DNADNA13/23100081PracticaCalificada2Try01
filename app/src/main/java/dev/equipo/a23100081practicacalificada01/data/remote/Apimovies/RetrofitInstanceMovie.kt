package dev.equipo.a23100081practicacalificada01.data.remote.Apimovies


import dev.equipo.a23100081practicacalificada01.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.themoviedb.org/3/"

object RetrofitInstanceMovie {

    private val client = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer ${BuildConfig.API_MOVIE_KEY}")
                .addHeader("Accept", "application/json")
                .build()
            chain.proceed(request)
        }
        .build()

    val api: ApiMovieService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiMovieService::class.java)
    }
}
