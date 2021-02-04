package clandestino.giphymvvmapp.network.api

import clandestino.giphymvvmapp.models.GiphyObject
import clandestino.giphymvvmapp.models.SearchResponse
import clandestino.giphymvvmapp.ui.trending.viewModels.TrendingListViewModel
import io.reactivex.Single
import retrofit2.http.GET
import com.giphy.sdk.core.network.response.ListMediaResponse
import com.giphy.sdk.core.network.response.RandomGifResponse
import retrofit2.http.Query

interface GiphyApi {
    @GET("gifs/trending")
    fun trendingGifs(
        @Query("offset") page: Int,
        @Query("limit") pageSize: Int = TrendingListViewModel.pageSize
    ): Single<ListMediaResponse>

    @GET("gifs/random")
    fun randomGif(): Single<RandomGifResponse>

    @GET("gifs/search")
    fun search(@Query("q") queryString: String): Single<SearchResponse>
}