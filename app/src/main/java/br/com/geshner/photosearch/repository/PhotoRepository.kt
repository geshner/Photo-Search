package br.com.geshner.photosearch.repository

import br.com.geshner.photosearch.model.Photo
import br.com.geshner.photosearch.webclient.PexelsWebClient

class PhotoRepository(private val webClient: PexelsWebClient = PexelsWebClient()) {

    suspend fun searchPhotos(query: String): Resource {
        val response = webClient.searchPhotos(query)

        val resource = response?.let {
            val photos = it.photos.map { pexelsPhoto ->
                Photo(
                    id = pexelsPhoto.id,
                    photographer = pexelsPhoto.photographer,
                    alt = pexelsPhoto.alt,
                    src = pexelsPhoto.src.original
                )
            }
            Resource.Success(photos)

        } ?: Resource.Error(Exception("no image fetched"))

        return resource
    }
}