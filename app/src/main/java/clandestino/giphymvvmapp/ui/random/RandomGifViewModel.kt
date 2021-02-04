package clandestino.giphymvvmapp.ui.random

import android.util.Log
import androidx.lifecycle.*
import clandestino.giphymvvmapp.data.GiphyDataManager
import clandestino.giphymvvmapp.data.RandomGifEntity
import clandestino.giphymvvmapp.network.GiphyService
import com.giphy.sdk.core.models.Image
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RandomGifViewModel(dataManager: GiphyDataManager) : ViewModel(), LifecycleObserver {

    private val disposibles = CompositeDisposable()
    var randomGif = MutableLiveData<RandomGifEntity?>()

    init {
        disposibles.add(dataManager.randomGif()
            .subscribe({
                randomGif.postValue(it)
            }, {
                Log.e("RandomGifViewModel", it.localizedMessage, it)
            })
        )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        if (!disposibles.isDisposed) {
            disposibles.clear()
        }
    }
}