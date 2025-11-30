package dev.equipo.a23100081practicacalificada01.data.remote.ApiBaraja


import dev.equipo.a23100081practicacalificada01.data.model.DeckResponse
import dev.equipo.a23100081practicacalificada01.data.model.DrawResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DeckApi {
    @GET("api/deck/new/shuffle/")
    suspend fun createDeck(@Query("deck_count") count: Int = 1): DeckResponse

    @GET("api/deck/{deck_id}/draw/")
    suspend fun drawCards(
        @Path("deck_id") deckId: String,
        @Query("count") count: Int
    ): DrawResponse
}

object DeckService {
    val api: DeckApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://deckofcardsapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DeckApi::class.java)
    }
}
