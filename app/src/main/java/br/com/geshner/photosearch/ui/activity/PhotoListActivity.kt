package br.com.geshner.photosearch.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.geshner.photosearch.R
import br.com.geshner.photosearch.model.Photo
import br.com.geshner.photosearch.ui.recyclerviewadapter.PhotoListAdapter
import br.com.geshner.photosearch.webclient.PexelsWebClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class PhotoListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_list)

        CoroutineScope(IO).launch {
            val response = PexelsWebClient().searchPhotos("nature")
            Log.i("TAG", "nature photos: $response")
        }

        val recyclerView = findViewById<RecyclerView>(R.id.activity_photo_list_recyclerview)

        val photos = List(5) { index ->
            Photo(
                index.toString(),
                "Photographer $index",
                "Photo $index description",
                ""
            )
        }

        val adapter = PhotoListAdapter(this, photos)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}