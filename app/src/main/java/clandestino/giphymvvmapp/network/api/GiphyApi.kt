package clandestino.giphymvvmapp.network.api

import clandestino.giphymvvmapp.models.SearchResponse
import clandestino.giphymvvmapp.ui.trending.viewModels.TrendingListViewModel
import retrofit2.http.GET
import com.giphy.sdk.core.network.response.ListMediaResponse
import com.giphy.sdk.core.network.response.RandomGifResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Query

interface GiphyApi {
    @GET("gifs/trending")
    fun trendingGifs(
        @Query("offset") offset: Int,
        @Query("limit") pageSize: Int = TrendingListViewModel.pageSize
    ): Single<ListMediaResponse>

    @GET("gifs/random")
    fun randomGif(): Single<RandomGifResponse>

    @GET("gifs/search")
    fun search(@Query("q") queryString: String): Single<SearchResponse>
}