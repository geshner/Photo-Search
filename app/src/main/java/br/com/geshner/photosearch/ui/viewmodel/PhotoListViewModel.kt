package br.com.geshner.photosearch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.com.geshner.photosearch.repository.PhotoRepository
import br.com.geshner.photosearch.repository.Resource

class PhotoListViewModel : ViewModel() {

    private val photoRepository by lazy {
        PhotoRepository()
    }

    private val queryString = MutableLiveData<String>()

    val photos: LiveData<Resource>
        get() = Transformations.switchMap(queryString) { query ->
            photoRepository.searchPhotos(query)
        }

    fun queryPhotos(query: String) {
        queryString.value = query
    }
}