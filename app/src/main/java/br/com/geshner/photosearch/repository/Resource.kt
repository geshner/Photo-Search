package br.com.geshner.photosearch.repository

sealed class Resource {
    class Success(val data: Any): Resource()
    class Error(val e: Throwable): Resource()
}
