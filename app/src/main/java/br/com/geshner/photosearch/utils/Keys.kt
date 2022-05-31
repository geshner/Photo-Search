package br.com.geshner.photosearch.utils

object Keys {
    init {
        System.loadLibrary("native-lib")
    }

    external fun pexelsApiKey(): String
}