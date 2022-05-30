package br.com.geshner.photosearch.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.geshner.photosearch.R
import br.com.geshner.photosearch.model.Photo
import br.com.geshner.photosearch.repository.PhotoRepository
import br.com.geshner.photosearch.repository.Resource
import br.com.geshner.photosearch.ui.recyclerviewadapter.PhotoListAdapter
import br.com.geshner.photosearch.webclient.PexelsWebClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotoListActivity : AppCompatActivity() {


    private val adapter by lazy {
        PhotoListAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_list)

        val recyclerView = findViewById<RecyclerView>(R.id.activity_photo_list_recyclerview)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        adapter.clickListener = {
            val intent = Intent(
                this,
                PhotoViewerActivity::class.java
            ).apply {
                putExtra("Photo", it)
            }
            startActivity(intent)
        }

    }

    private fun fetchPhotos(query: String) {
        CoroutineScope(IO).launch {
            when (val response = PhotoRepository().searchPhotos(query)) {
                is Resource.Success -> withContext(Main) { adapter.update(response.data as List<Photo>) }
                is Resource.Error -> {}
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        (menu?.findItem(R.id.menu_search)?.actionView as SearchView).let { searchView ->
            searchView.isIconified = true
            searchView.clearFocus()
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.i("TAG", "onQueryTextSubmit: $query")
                    query?.let {
                        fetchPhotos(it)
                        searchView.clearFocus()
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean = false

            })
        }
        return super.onCreateOptionsMenu(menu)
    }
}