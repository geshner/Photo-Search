package br.com.geshner.photosearch.webclient.model

// Representation of Pexels photo search response
data class PexelsPhotoSearchResponse(
    val totalResults: Int,
    val page: Int,
    val per_page: Int,
    val photos: List<PexelsPhotoResponse>,
    val next_page: String? = null,
)
