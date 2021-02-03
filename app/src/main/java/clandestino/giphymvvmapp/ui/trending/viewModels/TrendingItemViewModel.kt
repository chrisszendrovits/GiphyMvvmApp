package clandestino.giphymvvmapp.ui.trending.viewModels

import androidx.lifecycle.ViewModel
import com.giphy.sdk.core.models.Media
import com.giphy.sdk.core.models.Image

class TrendingItemViewModel(var id: String, var image: Image): ViewModel() {
    companion object {
        fun convertTo(list: List<Media>?) : List<TrendingItemViewModel> {
            val viewModels = mutableListOf<TrendingItemViewModel>()
            list?.let {
                for (m in list) {
                    m.images.downsizedMedium?.let {
                        viewModels.add(TrendingItemViewModel(m.id, it))
                    }
                }
            }
            return viewModels
        }
    }
}