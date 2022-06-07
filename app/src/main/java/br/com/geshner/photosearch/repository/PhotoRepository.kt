package br.com.geshner.photosearch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import br.com.geshner.photosearch.model.Photo
import br.com.geshner.photosearch.webclient.PexelsWebClient

class PhotoRepository(private val webClient: PexelsWebClient = PexelsWebClient()) {

    fun searchPhotos(query: String): LiveData<Resource> {
        return liveData {

            val response = webClient.searchPhotos(query)

            val resource = response?.let {
                if (it.total_results == 0) {
                    return@let Resource.Error(Exception("No results found"))
                }

                val photos = it.photos.map { pexelsPhoto ->
                    Photo(
                        id = pexelsPhoto.id,
                        photographer = pexelsPhoto.photographer,
                        alt = pexelsPhoto.alt,
                        src = pexelsPhoto.src.original
                    )
                }
                Resource.Success(photos)

            } ?: Resource.Error(Exception("Failed to fetch photos"))

            emit(resource)
        }
    }
}