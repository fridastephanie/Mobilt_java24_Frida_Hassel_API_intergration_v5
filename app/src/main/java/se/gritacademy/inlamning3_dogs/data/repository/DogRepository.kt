package se.gritacademy.inlamning3_dogs.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import se.gritacademy.inlamning3_dogs.data.api.DogApiService
import se.gritacademy.inlamning3_dogs.data.model.Breed

class DogRepository(private val api: DogApiService) {

    /**
     * Fetches a list of dog breeds from the API.
     */
    suspend fun getBreeds(): List<Breed> {
        return withContext(Dispatchers.IO) {
            val response = api.getBreeds()
            if (response.isSuccessful) {
                response.body()?.map { mapToBreed(it) } ?: emptyList()
            } else emptyList()
        }
    }

    /**
     * Converts a raw Map<String, Any?> from the API into a Breed object.
     * Extracts weight, height, image URL, and other breed properties safely.
     */
    private fun mapToBreed(item: Map<String, Any?>): Breed {
        val weight = (item["weight"] as? Map<*, *>)?.get("metric") as? String ?: "N/A"
        val height = (item["height"] as? Map<*, *>)?.get("metric") as? String ?: "N/A"
        val imageUrl = (item["image"] as? Map<*, *>)?.get("url") as? String
        return Breed(
            id = (item["id"] as? Double)?.toInt() ?: 0,
            name = item["name"] as? String ?: "N/A",
            temperament = item["temperament"] as? String ?: "N/A",
            life_span = item["life_span"] as? String ?: "N/A",
            weight = weight,
            height = height,
            imageUrl = imageUrl
        )
    }

    /**
     * Fetches only the image URL for a specific breed by its id.
     * Returns null if no image is found or if the API call fails.
     */
    suspend fun getBreedImage(breedId: Int): String? {
        return withContext(Dispatchers.IO) {
            val response = api.getBreedImage(breedId)
            if (response.isSuccessful) {
                val body = response.body()
                if (!body.isNullOrEmpty()) {
                    body[0]["url"] as? String
                } else null
            } else null
        }
    }
}