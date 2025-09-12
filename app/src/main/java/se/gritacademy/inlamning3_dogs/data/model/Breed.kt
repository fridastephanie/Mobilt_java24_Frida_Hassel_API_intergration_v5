package se.gritacademy.inlamning3_dogs.data.model

data class Breed(
    val id: Int,
    val name: String,
    val temperament: String?,
    val life_span: String?,
    val weight: String?,
    val height: String?,
    var imageUrl: String?
)
