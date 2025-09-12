package se.gritacademy.inlamning3_dogs.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/** Singleton object providing a Retrofit instance of [DogApiService] */
object ApiClient {
    val dogApiService: DogApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thedogapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogApiService::class.java)
    }
}
