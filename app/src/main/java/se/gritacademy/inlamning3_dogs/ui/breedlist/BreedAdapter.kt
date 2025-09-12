package se.gritacademy.inlamning3_dogs.ui.breedlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import se.gritacademy.inlamning3_dogs.R
import se.gritacademy.inlamning3_dogs.data.model.Breed

class BreedAdapter(
    private var breeds: List<Breed> = emptyList(),
    private val onClick: (Breed) -> Unit
) : RecyclerView.Adapter<BreedAdapter.BreedViewHolder>() {

    /** Holds reference to the breed name TextView */
    class BreedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: TextView = itemView.findViewById(R.id.textBreedName)
    }

    /** Inflates the item layout and returns a ViewHolder */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_breed, parent, false)
        return BreedViewHolder(view)
    }

    /** Returns the total number of items */
    override fun getItemCount(): Int = breeds.size

    /** Binds breed data to the item view */
    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        val breed = breeds[position]
        holder.textName.text = breed.name
        holder.itemView.setOnClickListener { onClick(breed) }
    }

    /** Updates the adapters breed list and refreshes the view */
    fun updateBreeds(newBreeds: List<Breed>) {
        breeds = newBreeds
        notifyDataSetChanged()
    }
}