package ggum.oo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ggum.oo.data.service.MypageService
import ggum.oo.data.service.TestService
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    private inline fun <reified T> Retrofit.buildService(): T {
        return this.create(T::class.java)
    }

    @Provides
    @Singleton
    fun provideTestService(retrofit: Retrofit): TestService {
        return retrofit.buildService()
    }

    @Provides
    fun provideMypageService(retrofit: Retrofit): MypageService {
        return retrofit.buildService() // 적절한 구현체로 교체
    }
}