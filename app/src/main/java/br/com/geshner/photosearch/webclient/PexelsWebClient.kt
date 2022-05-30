package br.com.geshner.photosearch.webclient

import android.util.Log
import br.com.geshner.photosearch.webclient.model.PexelsPhotoSearchResponse

private const val TAG = "PexelsWebClient"

// Web client to execute Pexels API calls
class PexelsWebClient {

    private val pexelsService = AppRetrofit().pexelsService


    suspend fun searchPhotos(query: String): PexelsPhotoSearchResponse? {
        return try {
            val response = pexelsService.search(query)

            response.body()
        } catch (e: Exception) {
            Log.e(TAG, "Fail to search photos", e)
            null
        }
    }

}