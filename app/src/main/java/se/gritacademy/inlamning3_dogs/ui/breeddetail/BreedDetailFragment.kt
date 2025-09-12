package se.gritacademy.inlamning3_dogs.ui.breeddetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.coroutines.*
import se.gritacademy.inlamning3_dogs.R
import se.gritacademy.inlamning3_dogs.data.api.ApiClient
import se.gritacademy.inlamning3_dogs.data.model.Breed
import se.gritacademy.inlamning3_dogs.data.repository.DogRepository
import se.gritacademy.inlamning3_dogs.data.repository.FavoriteRepository
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.graphics.Typeface

class BreedDetailFragment : Fragment() {

    private lateinit var breed: Breed
    private lateinit var textName: TextView
    private lateinit var textTemperament: TextView
    private lateinit var textLifeSpan: TextView
    private lateinit var textWeight: TextView
    private lateinit var textHeight: TextView
    private lateinit var imageBreed: ImageView
    private lateinit var buttonFavorite: Button

    private lateinit var favoriteRepository: FavoriteRepository

    /** Inflates the layout and initializes views and repository. */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_breed_detail, container, false)

        initViews(view)
        favoriteRepository = FavoriteRepository(requireContext())

        val breedId = arguments?.getInt("breedId") ?: 0
        fetchBreedDetail(breedId)

        return view
    }

    /** Finds all required views by ID. */
    private fun initViews(view: View) {
        textName = view.findViewById(R.id.textName)
        textTemperament = view.findViewById(R.id.textTemperament)
        textLifeSpan = view.findViewById(R.id.textLifeSpan)
        textWeight = view.findViewById(R.id.textWeight)
        textHeight = view.findViewById(R.id.textHeight)
        imageBreed = view.findViewById(R.id.imageBreedDetail)
        buttonFavorite = view.findViewById(R.id.buttonFavorite)
    }

    /** Fetches breed details and image asynchronously. */
    private fun fetchBreedDetail(breedId: Int) {
        val repository = DogRepository(ApiClient.dogApiService)

        CoroutineScope(Dispatchers.Main).launch {
            val breeds = withContext(Dispatchers.IO) { repository.getBreeds() }
            breed = breeds.find { it.id == breedId } ?: return@launch
            breed = breed.copy(
                imageUrl = withContext(Dispatchers.IO) { repository.getBreedImage(breedId) }
            )
            setupUI()
        }
    }

    /** Updates all UI elements for the current breed. */
    private fun setupUI() {
        updateTextViews()
        updateImageView()
        setupFavoriteButton()
    }

    /** Updates the text fields with breed data. */
    private fun updateTextViews() {
        textName.text = breed.name
        textTemperament.text = formatLabelValue("Temperament: ", breed.temperament ?: "N/A")
        textLifeSpan.text = formatLabelValue("Life Span: ", breed.life_span ?: "N/A")
        textWeight.text = formatLabelValue("Weight: ", breed.weight ?: "N/A")
        textHeight.text = formatLabelValue("Height: ", breed.height ?: "N/A")
    }

    /** Formats label (bold) + value (normal) */
    private fun formatLabelValue(label: String, value: String): CharSequence {
        return SpannableStringBuilder().apply {
            append(label)
            setSpan(
                StyleSpan(Typeface.BOLD),
                0,
                label.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            append("\n")
            append(value)
        }
    }

    /** Loads breed image with Glide if available. */
    private fun updateImageView() {
        breed.imageUrl?.let { url ->
            Glide.with(this)
                .load(url)
                .into(imageBreed)
        }
    }

    /** Sets up favorite button and click handling. */
    private fun setupFavoriteButton() {
        val isFav = favoriteRepository.isFavorite(breed.id)
        updateFavoriteButton(isFav)

        buttonFavorite.setOnClickListener {
            toggleFavorite()
        }
    }

    /** Toggles the favorite state of the current breed. */
    private fun toggleFavorite() {
        if (favoriteRepository.isFavorite(breed.id)) {
            favoriteRepository.removeFavorite(breed.id)
            updateFavoriteButton(false)
        } else {
            favoriteRepository.addFavorite(breed.id)
            updateFavoriteButton(true)
        }
    }

    /** Updates the favorite button text based on current state. */
    private fun updateFavoriteButton(isFav: Boolean) {
        buttonFavorite.text =
            if (isFav) "Remove from Favorites" else "Add to Favorites"
    }
}