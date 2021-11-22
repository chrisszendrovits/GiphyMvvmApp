package clandestino.giphymvvmapp.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class ImageViewBindings {
    @BindingAdapter("url")
    fun ImageView.loadUrl(imageUrl: String?) {
        imageUrl?.let {
            Glide.with(context).load(it).into(this);
        }
    }
}