package br.com.geshner.photosearch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.geshner.photosearch.webclient.PexelsWebClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(IO).launch {
            val response = PexelsWebClient().searchPhotos("nature")
            Log.i("TAG", "nature photos: $response")
        }
    }
}