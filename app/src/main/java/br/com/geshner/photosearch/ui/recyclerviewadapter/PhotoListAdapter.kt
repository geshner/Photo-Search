package br.com.geshner.photosearch.ui.recyclerviewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.geshner.photosearch.R
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

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val srcImage = view.findViewById<ImageView>(R.id.photo_list_item_image_view)
        private val photographerText =
            view.findViewById<TextView>(R.id.photo_list_item_photographer)
        private lateinit var  photo: Photo

        init {
            itemView.setOnClickListener{
                if(::photo.isInitialized) {
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
        val view = inflater.inflate(R.layout.photo_list_item, parent, false)

        return ViewHolder(view)
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