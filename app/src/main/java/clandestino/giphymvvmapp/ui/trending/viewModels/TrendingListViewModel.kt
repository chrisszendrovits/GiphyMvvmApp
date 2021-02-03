package clandestino.giphymvvmapp.ui.trending.viewModels

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import clandestino.giphymvvmapp.ui.trending.datasource.TrendingListDatasourceFactory

class TrendingListViewModel: ViewModel(), LifecycleObserver {

    companion object {
        val pageSize = 50
    }

    private val datasourceFactory = TrendingListDatasourceFactory()
    var trendingList: LiveData<PagedList<TrendingItemViewModel>>
        private set

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()

        trendingList = LivePagedListBuilder<String, TrendingItemViewModel>(datasourceFactory, config).build()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        datasourceFactory.clear()
    }
}