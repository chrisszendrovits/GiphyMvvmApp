package clandestino.giphymvvmapp.ui.trending.viewModels

import androidx.lifecycle.ViewModel
import com.giphy.sdk.core.models.Image
import com.giphy.sdk.core.models.Media

class TrendingItemViewModel(var id: String? = null, var image: Image? = null): ViewModel() {
    companion object {
        fun convertFrom(media: Media) : TrendingItemViewModel {
            var viewModel = TrendingItemViewModel()
            media.images.downsizedMedium?.let {
                viewModel.id = media.id
                viewModel.image = it
            }
            return viewModel
        }
    }
}