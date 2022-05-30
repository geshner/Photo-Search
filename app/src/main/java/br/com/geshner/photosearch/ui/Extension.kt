package br.com.geshner.photosearch.ui

import android.app.Activity
import android.widget.ImageView
import android.widget.Toast
import br.com.geshner.photosearch.R
import coil.load
import coil.size.ViewSizeResolver

fun Activity.displayErrorMessage(message: String) {
    Toast.makeText(
        this,
        message,
        Toast.LENGTH_LONG
    ).show()
}

fun ImageView.loadImage(url: String) {
    this.load(url) {
        crossfade(true)
        placeholder(R.drawable.image_placeholder)
        error(R.drawable.no_image_available)
        size(ViewSizeResolver(this@loadImage))
    }
}