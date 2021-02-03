package clandestino.giphymvvmapp.ui.widgets

import androidx.databinding.DataBindingComponent

class AppDataBindingComponent : DataBindingComponent {
    override fun getImageViewBindings(): ImageViewBindings {
        return ImageViewBindings()
    }
}