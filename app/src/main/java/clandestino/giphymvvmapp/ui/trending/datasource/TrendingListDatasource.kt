package clandestino.giphymvvmapp.ui.trending.datasource

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import clandestino.giphymvvmapp.network.GiphyService
import clandestino.giphymvvmapp.ui.trending.viewModels.TrendingItemViewModel
import io.reactivex.disposables.CompositeDisposable

class TrendingListDatasource(private val disposibles: CompositeDisposable): ItemKeyedDataSource<String, TrendingItemViewModel>() {

    private val giphyApi = GiphyService().getApi()
    private var page: Int = 0

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<TrendingItemViewModel>) {
        disposibles.add(giphyApi.trendingGifs(page++)
            .map { TrendingItemViewModel.convertTo(it.data) }
            .subscribe({ list -> callback.onResult(list) }, { t -> logError(t) })
        )
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<TrendingItemViewModel>) {
        disposibles.add(giphyApi.trendingGifs(page++)
            .map { TrendingItemViewModel.convertTo(it.data) }
            .subscribe({ list -> callback.onResult(list) }, { t -> logError(t) })
        )
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<TrendingItemViewModel>) { }

    override fun getKey(item: TrendingItemViewModel): String {
        return item.id
    }

    private fun logError(throwable: Throwable) {
        Log.e("TrendingListDatasource", throwable.localizedMessage, throwable);
    }
}

class TrendingListDatasourceFactory: DataSource.Factory<String, TrendingItemViewModel>() {
    private val disposibles = CompositeDisposable()

    override fun create(): DataSource<String, TrendingItemViewModel> {
        return TrendingListDatasource(disposibles)
    }

    fun clear() {
        if (!disposibles.isDisposed) {
            disposibles.clear()
        }
    }
}
