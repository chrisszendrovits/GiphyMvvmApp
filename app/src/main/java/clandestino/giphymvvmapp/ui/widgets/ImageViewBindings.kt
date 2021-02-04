package clandestino.giphymvvmapp.ui.widgets

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class ImageViewBindings {
    @BindingAdapter("bind:url")
    fun ImageView.loadUrl(imageUrl: String?) {
        imageUrl?.let {
            Glide.with(context).load(it).into(this);
        }
    }
}