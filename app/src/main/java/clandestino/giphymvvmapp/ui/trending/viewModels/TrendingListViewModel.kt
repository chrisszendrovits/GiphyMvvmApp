package clandestino.giphymvvmapp.ui.trending.viewModels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import clandestino.giphymvvmapp.ui.trending.datasource.TrendingListPagingSourceFactory
import com.giphy.sdk.core.models.Media

class TrendingListViewModel: ViewModel() {

    companion object {
        val pageSize = 50

        fun convertFrom(list: List<Media>?) : List<TrendingItemViewModel> {
            val viewModels = mutableListOf<TrendingItemViewModel>()
            list?.let {
                for (m in list) {
                    viewModels.add(TrendingItemViewModel.convertFrom(m))
                }
            }
            return viewModels
        }
    }

    private val pager = TrendingListPagingSourceFactory()

    var trendingList: LiveData<PagingData<TrendingItemViewModel>>
        private set

    init {
        trendingList = pager.getPagingData().cachedIn(viewModelScope)
    }
}

