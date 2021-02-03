package clandestino.giphymvvmapp.models

import com.giphy.sdk.core.models.Meta
import com.giphy.sdk.core.models.Pagination
import com.giphy.sdk.core.network.response.GenericResponse

data class SearchResponse(
        var data: List<GiphyObject>?,
        var meta: Meta?,
        var pagination: Pagination?
) : GenericResponse