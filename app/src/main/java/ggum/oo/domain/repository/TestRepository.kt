package ggum.oo.domain.repository

import ggum.oo.data.dto.request.TestRequest
import ggum.oo.domain.model.TestModel
import ggum.oo.util.network.NetworkResult


interface TestRepository {
    suspend fun fetchTest(request: TestRequest): NetworkResult<TestModel>
}