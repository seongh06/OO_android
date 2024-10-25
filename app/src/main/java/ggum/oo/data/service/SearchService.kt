package ggum.oo.data.service

import android.util.Log
import ggum.oo.data.dto.BaseResponse
import ggum.oo.data.dto.NoneBaseResponse
import ggum.oo.data.dto.SearchBaseResponse
import ggum.oo.data.dto.request.SignUpLoginRequestDto
import ggum.oo.data.dto.response.SearchResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface SearchService {

    @GET("/api/search")
    suspend fun search(
        @Query ("keyword") keyword: String
    ):BaseResponse<SearchResponseDto>

    @GET("/api/searchLogs")
    suspend fun searchHistory(
        @Query("userId") userId: Int
    ): SearchBaseResponse<String>
}