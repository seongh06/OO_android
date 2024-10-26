package ggum.oo.domain.repository

import ggum.oo.domain.model.response.SearchResponseModel

interface SearchRepository {
    suspend fun search(keyword:String):Result<SearchResponseModel>

    suspend fun search(userId: Int):Result<String>
}