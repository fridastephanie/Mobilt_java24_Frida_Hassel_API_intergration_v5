package se.gritacademy.inlamning3_dogs.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import se.gritacademy.inlamning3_dogs.R
import se.gritacademy.inlamning3_dogs.data.model.Breed

class FavoritesAdapter(
    private var favorites: List<Breed>
) : RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {

    /** ViewHolder holding favorite item views */
    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textName: TextView = itemView.findViewById(R.id.textFavoriteName)
        private val textTemperament: TextView = itemView.findViewById(R.id.textFavoriteTemperament)

        /** Binds breed data to the view */
        fun bind(breed: Breed) {
            textName.text = breed.name
            textTemperament.text = breed.temperament ?: "N/A"
        }
    }

    /** Inflates item layout and creates ViewHolder */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    /** Returns the number of favorite items */
    override fun getItemCount(): Int = favorites.size

    /** Binds ViewHolder with data */
    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favorites[position])
    }
}