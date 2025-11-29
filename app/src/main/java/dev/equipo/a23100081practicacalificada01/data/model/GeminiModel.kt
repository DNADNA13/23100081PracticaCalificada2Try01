package dev.equipo.a23100081practicacalificada01.data.model

data class Part (
    val text: String
)
//Gemini Request
data class Content (
    val parts: List<Part>
)

data class GeminiRequest(
    val contents: List<Content>
)

//Gemini Response
data class Candidate (
    val content: Content
)

data class GeminiResponse (
    val candidates: List<Candidate>
)
