package se.gritacademy.inlamning3_dogs.ui.breedlist

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.*
import se.gritacademy.inlamning3_dogs.R
import se.gritacademy.inlamning3_dogs.data.api.ApiClient
import se.gritacademy.inlamning3_dogs.data.model.Breed
import se.gritacademy.inlamning3_dogs.data.repository.DogRepository

class BreedListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val breedAdapter = BreedAdapter(emptyList()) { breed ->
        navigateToDetail(breed.id)
    }

    /** Inflates layout and sets up RecyclerView */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_breed_list, container, false)
        setupRecyclerView(view)
        fetchBreeds()
        return view
    }

    /**
     * Initializes RecyclerView and attaches adapter.
     * Uses GridLayout with 2 columns if device is in landscape,
     * otherwise LinearLayoutManager with 1 column.
     */
    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerViewBreeds)

        recyclerView.layoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(requireContext(), 2)
        } else {
            LinearLayoutManager(requireContext())
        }

        recyclerView.adapter = breedAdapter
        recyclerView.setPadding(8, 8, 8, 8)
        recyclerView.clipToPadding = false
    }

    /** Navigate to BreedDetailFragment with breedId */
    private fun navigateToDetail(breedId: Int) {
        val action = BreedListFragmentDirections.actionBreedListToDetail(breedId)
        findNavController().navigate(action)
    }

    /** Fetches breeds from API and updates adapter */
    private fun fetchBreeds() {
        val repository = DogRepository(ApiClient.dogApiService)
        CoroutineScope(Dispatchers.Main).launch {
            val breeds: List<Breed> = withContext(Dispatchers.IO) {
                repository.getBreeds()
            }
            breedAdapter.updateBreeds(breeds)
        }
    }
}