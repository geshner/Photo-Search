package br.com.geshner.photosearch.webclient.model

// Representation of Pexels photo search response
data class PexelsPhotoSearchResponse(
    val total_results: Int,
    val page: Int,
    val per_page: Int,
    val photos: List<PexelsPhotoResponse>,
    val next_page: String? = null,
)
