package br.com.geshner.photosearch.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import br.com.geshner.photosearch.R
import br.com.geshner.photosearch.databinding.ActivityPhotoListBinding
import br.com.geshner.photosearch.model.Photo
import br.com.geshner.photosearch.repository.Resource
import br.com.geshner.photosearch.ui.displayErrorMessage
import br.com.geshner.photosearch.ui.recyclerviewadapter.PhotoListAdapter
import br.com.geshner.photosearch.ui.viewmodel.PhotoListViewModel

class PhotoListActivity : AppCompatActivity() {


    private val adapter by lazy {
        PhotoListAdapter(context = this, clickListener = {
            val intent = Intent(
                this,
                PhotoViewerActivity::class.java
            ).apply {
                putExtra("Photo", it)
            }
            startActivity(intent)
        })
    }

    private val binding by lazy {
        ActivityPhotoListBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        val provider = ViewModelProvider(this)
        provider[PhotoListViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureRecyclerView()
    }

    override fun onResume() {
        super.onResume()

        viewModel.photos.observe(this) { resource ->
            binding.activityPhotoListProgress.hide()
            when (resource) {
                is Resource.Success -> {
                    adapter.update(resource.data as List<Photo>)
                }
                is Resource.Error -> {
                    displayErrorMessage(resource.e.message ?: "Failed to load Image")
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        (menu?.findItem(R.id.menu_search)?.actionView as SearchView).let { searchView ->
            searchView.isIconified = true
            searchView.clearFocus()
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
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

    //Setup photo list recyclerview
    private fun configureRecyclerView() {
        binding.activityPhotoListRecyclerview.also {
            it.adapter = adapter
            it.layoutManager = GridLayoutManager(this, 2)
        }
    }

    //Get photos from API based on query string
    private fun fetchPhotos(query: String) {
        binding.activityPhotoListProgress.show()
        viewModel.queryPhotos(query)
    }
}