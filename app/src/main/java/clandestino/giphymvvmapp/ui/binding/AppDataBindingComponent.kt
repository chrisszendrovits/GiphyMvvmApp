package clandestino.giphymvvmapp.ui.binding

import androidx.databinding.DataBindingComponent

class AppDataBindingComponent : DataBindingComponent {
    override fun getImageViewBindings(): ImageViewBindings {
        return ImageViewBindings()
    }
}