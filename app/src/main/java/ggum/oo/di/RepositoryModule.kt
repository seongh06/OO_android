package ggum.oo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ggum.oo.data.datasource.LoginDataSource
import ggum.oo.data.repositoryImpl.LoginRepositoryImpl
import ggum.oo.data.repositoryImpl.TestRepositoryImpl
import ggum.oo.data.service.LoginService
import ggum.oo.data.service.TestService
import ggum.oo.domain.repository.LoginRepository
import ggum.oo.domain.repository.TestRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    // 스코프 애노테이션이 있음
    // 해당하는 Hilt 컴포넌트의 수명동안 매 요청에 동일 인스턴스를 반환
    // 다음의 경우 viewModel의 수명동안 동일 인스턴스를 반환
    @ViewModelScoped
    @Provides
    fun providesTestRepository(
        testService: TestService
    ): TestRepository = TestRepositoryImpl(testService)

    @Provides
    fun provideLoginRepository(loginDataSource: LoginDataSource): LoginRepository {
        return LoginRepositoryImpl(loginDataSource)
    }
}