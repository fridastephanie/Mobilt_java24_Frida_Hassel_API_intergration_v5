package se.gritacademy.inlamning3_dogs.data.repository

import android.content.Context
import android.content.SharedPreferences

class FavoriteRepository(context: Context) {
    /** SharedPreferences for storing favorite breed IDs locally. */
    private val prefs: SharedPreferences =
        context.getSharedPreferences("dog_prefs", Context.MODE_PRIVATE)

    /** Adds a breed ID to favorites. */
    fun addFavorite(breedId: Int) {
        val favorites = getFavorites().toMutableSet()
        favorites.add(breedId.toString())
        prefs.edit().putStringSet("favorites", favorites).apply()
    }

    /** Removes a breed ID from favorites. */
    fun removeFavorite(breedId: Int) {
        val favorites = getFavorites().toMutableSet()
        favorites.remove(breedId.toString())
        prefs.edit().putStringSet("favorites", favorites).apply()
    }

    /** Returns the set of all favorite breed IDs. */
    fun getFavorites(): Set<String> {
        return prefs.getStringSet("favorites", emptySet()) ?: emptySet()
    }

    /** Checks if a breed ID is marked as favorite. */
    fun isFavorite(breedId: Int): Boolean {
        return getFavorites().contains(breedId.toString())
    }

    /** Clears all favorites. */
    fun clearFavorites() {
        prefs.edit().remove("favorites").apply()
    }
}
