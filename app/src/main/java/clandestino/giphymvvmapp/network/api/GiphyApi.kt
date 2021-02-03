package clandestino.giphymvvmapp.network.api

import clandestino.giphymvvmapp.models.SearchResponse
import clandestino.giphymvvmapp.ui.trending.viewModels.TrendingListViewModel
import com.facebook.animated.gif.GifImage
import io.reactivex.Single
import retrofit2.http.GET
import com.giphy.sdk.core.network.response.ListMediaResponse
import retrofit2.http.Query

interface GiphyApi {
    @GET("gifs/trending")
    fun trendingGifs(
        @Query("offset") page: Int,
        @Query("limit") pageSize: Int = TrendingListViewModel.pageSize
    ): Single<ListMediaResponse>

    @GET("gifs/random")
    fun randomGif(): Single<GifImage>

    @GET("gifs/search")
    fun search(@Query("q") queryString: String): Single<SearchResponse>
}