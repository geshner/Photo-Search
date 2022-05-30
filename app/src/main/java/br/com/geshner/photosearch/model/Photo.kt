package br.com.geshner.photosearch.model

import java.io.Serializable

//Photo model for app
data class Photo(
    val id: Int,
    val photographer: String,
    val alt: String,
    val src: String,
) : Serializable