package ggum.oo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ggum.oo.data.datasource.LoginDataSource
import ggum.oo.data.datasource.MypageDataSource
import ggum.oo.data.datasourceImpl.LoginDataSourceImpl
import ggum.oo.data.datasourceImpl.MypageDataSourceImpl
import ggum.oo.data.service.LoginService
import ggum.oo.data.service.MypageService


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideLoginDataSource(loginService: LoginService): LoginDataSource {
        return LoginDataSourceImpl(loginService)
    }

    @Provides
    fun provideMypageDataSource(mypageService: MypageService): MypageDataSource {
        return MypageDataSourceImpl(mypageService) // 적절한 구현체로 교체
    }
}
