package se.gritacademy.inlamning3_dogs.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApiService {

    /** Fetches a list of dog breeds from the API */
    @GET("breeds")
    suspend fun getBreeds(): Response<List<Map<String, Any>>>

    /** Fetches an image for a specific breed using [breedId] */
    @GET("images/search")
    suspend fun getBreedImage(@Query("breed_id") breedId: Int): Response<List<Map<String, Any>>>
}