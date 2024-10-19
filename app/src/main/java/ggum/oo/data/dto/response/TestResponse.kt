package  ggum.oo.data.dto.response

import  ggum.oo.domain.model.TestModel


data class TestResponse (
    val body: String
){
    fun toTestModel() = TestModel(body)
}