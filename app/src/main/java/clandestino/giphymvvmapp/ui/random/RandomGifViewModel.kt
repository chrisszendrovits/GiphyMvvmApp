package clandestino.giphymvvmapp.ui.random

import android.util.Log
import androidx.lifecycle.*
import clandestino.giphymvvmapp.data.GiphyDataManager
import clandestino.giphymvvmapp.data.RandomGifEntity
import io.reactivex.rxjava3.disposables.CompositeDisposable

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