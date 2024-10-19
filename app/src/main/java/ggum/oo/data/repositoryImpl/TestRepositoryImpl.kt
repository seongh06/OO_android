package ggum.oo.data.repositoryImpl

import ggum.oo.data.dto.BaseResponse
import ggum.oo.data.dto.request.TestRequest
import ggum.oo.data.dto.response.TestResponse
import ggum.oo.data.service.TestService
import ggum.oo.domain.model.TestModel
import ggum.oo.domain.repository.TestRepository
import ggum.oo.util.network.NetworkResult
import ggum.oo.util.network.handleApi
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(
    private val testService: TestService
) : TestRepository {
    override suspend fun fetchTest(request: TestRequest): NetworkResult<TestModel> {
        return handleApi({testService.fetchTest(request)}) {response: BaseResponse<TestResponse> -> response.data.toTestModel()} // mapper를 통해 api 결과를 TestModel로 매핑
    }

}