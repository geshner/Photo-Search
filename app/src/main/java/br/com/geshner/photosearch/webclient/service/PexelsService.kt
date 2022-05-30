package br.com.geshner.photosearch.webclient.service

import br.com.geshner.photosearch.webclient.model.PexelsPhotoSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface PexelsService {

    @GET("search")
    suspend fun search(@Query("query") query: String): Response<PexelsPhotoSearchResponse>
}