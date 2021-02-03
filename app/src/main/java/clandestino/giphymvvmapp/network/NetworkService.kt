package clandestino.giphymvvmapp.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class NetworkService {

    protected fun getApiClient(
        baseUrl: String,
        apiKey: String,
        gson: Gson? = null
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson ?: GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getClient(apiKey))
            .build()
    }

    private fun getClient(apiKey: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain -> return@addInterceptor addApiKeyToChain(apiKey, chain) }
            .build()
    }

    private fun addApiKeyToChain(
        apiKey: String,
        chain: Interceptor.Chain
    ): Response {
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url()
        val newUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", apiKey).build()
        request.url(newUrl)
        return chain.proceed(request.build())
    }
}