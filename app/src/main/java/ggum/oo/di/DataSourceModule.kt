package ggum.oo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ggum.oo.data.datasource.LoginDataSource
import ggum.oo.data.datasourceImpl.LoginDataSourceImpl
import ggum.oo.data.service.LoginService

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideLoginDataSource(loginService: LoginService): LoginDataSource {
        return LoginDataSourceImpl(loginService)
    }
}
