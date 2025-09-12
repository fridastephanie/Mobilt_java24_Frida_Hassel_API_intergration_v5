package se.gritacademy.inlamning3_dogs.ui.favorites

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import se.gritacademy.inlamning3_dogs.R
import se.gritacademy.inlamning3_dogs.data.api.ApiClient
import se.gritacademy.inlamning3_dogs.data.repository.DogRepository
import se.gritacademy.inlamning3_dogs.data.repository.FavoriteRepository
import se.gritacademy.inlamning3_dogs.ui.breedlist.BreedAdapter

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private lateinit var favoriteRepository: FavoriteRepository
    private lateinit var repository: DogRepository
    private lateinit var adapter: BreedAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonClearFavorites: Button

    /** Initializes views, adapter, and click listeners */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRepositories()
        initViews(view)
        setupRecyclerView(view)
        initClickListeners()
        loadFavorites()
    }

    /** Initializes repositories for API and favorites storage */
    private fun initRepositories() {
        favoriteRepository = FavoriteRepository(requireContext())
        repository = DogRepository(ApiClient.dogApiService)
    }

    /** Finds views by ID */
    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerFavorites)
        buttonClearFavorites = view.findViewById(R.id.buttonClearFavorites)
    }

    /**
     * Sets up the RecyclerView adapter with click handling.
     * Uses GridLayoutManager with 2 columns if device is in landscape,
     * otherwise LinearLayoutManager with 1 column.
     */
    private fun setupRecyclerView(view: View) {
        adapter = BreedAdapter(onClick = { breed ->
            val action = FavoritesFragmentDirections
                .actionFavoritesFragmentToBreedDetailFragment(breed.id)
            findNavController().navigate(action)
        })

        recyclerView.adapter = adapter

        recyclerView.layoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(requireContext(), 2)
        } else {
            LinearLayoutManager(requireContext())
        }

        recyclerView.setPadding(8, 8, 8, 8)
        recyclerView.clipToPadding = false
    }

    /** Sets up click listeners for buttons */
    private fun initClickListeners() {
        buttonClearFavorites.setOnClickListener {
            clearFavoritesAndGoHome()
        }
    }

    /** Loads favorite breeds from repository and updates adapter */
    private fun loadFavorites() {
        CoroutineScope(Dispatchers.Main).launch {
            val breeds = withContext(Dispatchers.IO) { repository.getBreeds() }
            val favoriteIds = favoriteRepository.getFavorites().map { it.toInt() }
            val favoriteBreeds = breeds.filter { favoriteIds.contains(it.id) }
            adapter.updateBreeds(favoriteBreeds)
        }
    }

    /** Clears all favorites and navigates back to home, clearing backstack */
    private fun clearFavoritesAndGoHome() {
        favoriteRepository.clearFavorites()

        val navController = findNavController()
        navController.navigate(
            R.id.homeFragment,
            null,
            androidx.navigation.NavOptions.Builder()
                .setPopUpTo(navController.graph.startDestinationId, true)
                .build()
        )
    }
}