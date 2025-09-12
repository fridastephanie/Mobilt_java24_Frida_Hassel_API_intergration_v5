// ui/home/HomeFragment.kt
package se.gritacademy.inlamning3_dogs.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import se.gritacademy.inlamning3_dogs.R

class HomeFragment : Fragment() {

    private lateinit var btnBreedList: MaterialButton
    private lateinit var btnFavorites: MaterialButton

    /** Inflates layout and initializes views and click listeners */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        initViews(view)
        initClickListeners()
        return view
    }

    /** Finds buttons by ID */
    private fun initViews(view: View) {
        btnBreedList = view.findViewById(R.id.btnBreedList)
        btnFavorites = view.findViewById(R.id.btnFavorites)
    }

    /** Sets click listeners to navigate to other fragments */
    private fun initClickListeners() {
        btnBreedList.setOnClickListener { navigateToBreedList() }
        btnFavorites.setOnClickListener { navigateToFavorites() }
    }

    /** Navigates to Breed List fragment */
    private fun navigateToBreedList() {
        findNavController().navigate(R.id.breedListFragment)
    }

    /** Navigates to Favorites fragment */
    private fun navigateToFavorites() {
        findNavController().navigate(R.id.favoritesFragment)
    }
}