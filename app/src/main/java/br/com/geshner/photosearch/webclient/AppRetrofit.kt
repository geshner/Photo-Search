package br.com.geshner.photosearch.webclient

import br.com.geshner.photosearch.BuildConfig
import br.com.geshner.photosearch.webclient.service.PexelsService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.pexels.com/v1/"
private const val AUTH_HEADER = "Authorization"

class AppRetrofit {

    //Setup http client with interceptor to add authorization for each request
    private val client = OkHttpClient().newBuilder().addInterceptor { chain ->
        chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader(AUTH_HEADER, BuildConfig.PEXELS_KEY)
                .build()
        )
    }.build()

    // Setup Retrofit
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    // Create Pexels service
    val pexelsService: PexelsService = retrofit.create(PexelsService::class.java)


}