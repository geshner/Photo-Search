package br.com.geshner.photosearch.webclient.model

//Representation of Pexels Photo resource
data class PexelsPhotoResponse(
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val photographer: String,
    val photographerUrl: String,
    val photographerId: String,
    val avgColor: String,
    val src: PhotoSRC,
    val liked: Boolean,
    val alt: String,
) {

    //Representation of photo resource src
    data class PhotoSRC(
        val original: String,
        val large: String,
        val large2x: String,
        val medium: String,
        val small: String,
        val portrait: String,
        val landscape: String,
        val tiny: String,
    )
}
