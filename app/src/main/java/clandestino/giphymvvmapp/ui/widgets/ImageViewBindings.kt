package clandestino.giphymvvmapp.ui.widgets

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class ImageViewBindings {
    @BindingAdapter("bind:url")
    fun ImageView.loadUrl(imageUrl: String) {
        Glide.with(context).load(imageUrl).into(this);
    }
}