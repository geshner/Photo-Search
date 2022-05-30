package br.com.geshner.photosearch.ui.recyclerviewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.geshner.photosearch.R
import br.com.geshner.photosearch.databinding.PhotoListItemBinding
import br.com.geshner.photosearch.model.Photo
import coil.load
import coil.size.ViewSizeResolver

//Adapter for Photolist Recyclerview
class PhotoListAdapter(
    private val context: Context,
    var clickListener: (photo: Photo) -> Unit = {},
    photos: List<Photo> = listOf()
) : RecyclerView.Adapter<PhotoListAdapter.ViewHolder>() {

    private val photos = photos.toMutableList()

    inner class ViewHolder(binding: PhotoListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val srcImage = binding.photoListItemImageView
        private val photographerText = binding.photoListItemPhotographer
        private lateinit var photo: Photo

        init {
            itemView.setOnClickListener {
                if (::photo.isInitialized) {
                    clickListener(photo)
                }
            }
        }


        fun bind(photo: Photo) {
            this.photo = photo
            photographerText.text = photo.photographer
            srcImage.contentDescription = photo.alt
            srcImage.load(photo.src) {
                crossfade(true)
                placeholder(R.drawable.image_placeholder)
                error(R.drawable.no_image_available)
                size(ViewSizeResolver(srcImage))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = PhotoListItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photos[position]
        holder.bind(photo)
    }

    override fun getItemCount(): Int = this.photos.size

    fun update(list: List<Photo>) {
        photos.clear()
        photos.addAll(list)
        notifyDataSetChanged()
    }

}