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
    private val INITIAL_LOAD_SIZE = 1
    private val pageSize = TrendingListViewModel.pageSize

    override fun getRefreshKey(state: PagingState<Int, TrendingItemViewModel>): Int? {
        return null
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, TrendingItemViewModel>> {
        val position = params.key ?: INITIAL_LOAD_SIZE
        val offset = if (params.key != null) ((position - 1) * pageSize) + 1 else INITIAL_LOAD_SIZE

        return service.trendingGifs(offset, pageSize)
            .subscribeOn(Schedulers.io())
            .map { TrendingListViewModel.convertFrom(it) }
            .map { toLoadResult(it, position, params) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(
        list: List<TrendingItemViewModel>,
        position: Int,
        params: LoadParams<Int>
    ): LoadResult<Int, TrendingItemViewModel> {
        val nextKey = if (list.isEmpty()) {
            null
        } else {
            // initial load size = 3 * pageSize
            // ensure we're not requesting duplicating items, at the 2nd request
            position + (params.loadSize / pageSize)
        }
        return LoadResult.Page(
            data = list,
            prevKey = null, // Only page forward
            nextKey = nextKey
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
