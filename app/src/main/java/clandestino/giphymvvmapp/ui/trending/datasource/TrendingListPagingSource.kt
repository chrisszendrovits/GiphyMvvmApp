package clandestino.giphymvvmapp.ui.trending.datasource

import androidx.lifecycle.LiveData
import androidx.paging.*
import androidx.paging.rxjava3.RxPagingSource
import clandestino.giphymvvmapp.network.GiphyService
import clandestino.giphymvvmapp.ui.trending.viewModels.TrendingItemViewModel
import clandestino.giphymvvmapp.ui.trending.viewModels.TrendingListViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class TrendingListPagingSource: RxPagingSource<Int, TrendingItemViewModel>() {

    private val service = GiphyService.service

    override fun getRefreshKey(state: PagingState<Int, TrendingItemViewModel>): Int? {
        return null
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, TrendingItemViewModel>> {
        val page = params.key ?: 1

        return service.trendingGifs(page, TrendingListViewModel.pageSize)
            .subscribeOn(Schedulers.io())
            .map { TrendingListViewModel.convertFrom(it) }
            .map { toLoadResult(it, page) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(list: List<TrendingItemViewModel>, position: Int): LoadResult<Int, TrendingItemViewModel> {
        return LoadResult.Page(
            data = list,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (position == list.size) null else position + 1
        )
    }
}

class TrendingListPagingSourceFactory {
    fun getPagingData(pagingConfig: PagingConfig = getDefaultPageConfig()): LiveData<PagingData<TrendingItemViewModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { TrendingListPagingSource() }
        ).liveData
    }

    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = TrendingListViewModel.pageSize, enablePlaceholders = false)
    }
}
