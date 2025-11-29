package dev.equipo.a23100081practicacalificada01.data.remote.Apimovies
import retrofit2.http.GET
import retrofit2.http.Query

import retrofit2.http.Path


interface ApiMovieService {

    // Obtener películas favoritas de un usuario
    @GET("account/{account_id}/favorite/movies")
    suspend fun getFavoriteMovies(
        @Path("account_id") accountId: Int,
        @Query("page") page: Int = 1
    ): MovieResponse


    // Ejemplo: obtener películas populares
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1
    ): MovieResponse

    // Ejemplo: buscar película
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): MovieResponse
}