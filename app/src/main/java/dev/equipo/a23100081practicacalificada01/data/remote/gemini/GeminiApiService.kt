package dev.equipo.a23100081practicacalificada01.data.remote.gemini

import dev.equipo.a23100081practicacalificada01.data.model.GeminiRequest
import dev.equipo.a23100081practicacalificada01.data.model.GeminiResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface GeminiApiService {
    @POST("v1beta/models/gemini-2.5-flash:generateContent")
    suspend fun generateContent(
        @Query("key") apiKey: String,
        @Body request: GeminiRequest
    ): GeminiResponse
}