package ggum.oo.di

import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ggum.oo.OOApplication
import ggum.oo.R
import ggum.oo.data.service.LoginService
import ggum.oo.util.network.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

// @Module: 모듈은 Hilt에게 특정 객체를 만드는 방법을 알려주는 클래스이다.
@Module
// @InstallIn : 해당 컴포넌트의 모듈이 설치 되게 한다.
//별도로 Scope를 지정해주면 해당하는 HiltComponent의 수명동안 같은 인스턴스를 공유해 바인딩이 요청될 때마다 같은 인스턴스를 제공할 수 있다.
@InstallIn(SingletonComponent::class)
object NetworkModule {
    const val NETWORK_EXCEPTION_OFFLINE_CASE = "network status is offline"
    const val NETWORK_EXCEPTION_BODY_IS_NULL = "result.json body is null"

    @Provides
    @Singleton
    fun providesConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(
            GsonBuilder()
                .setLenient() // JSON 파싱에 유연성을 제공
                .create()
        )
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        spf: SharedPreferences,
        cookieJar: MyCookieJar // MyCookieJar 주입
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(spf, cookieJar)) // AuthInterceptor 추가
            .cookieJar(cookieJar) // CookieJar 추가
            .connectTimeout(30, TimeUnit.SECONDS) // 연결 타임아웃 설정
            .readTimeout(30, TimeUnit.SECONDS) // 읽기 타임아웃 설정
            .writeTimeout(30, TimeUnit.SECONDS) // 쓰기 타임아웃 설정
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY // 디버깅을 위한 로깅 설정
            })
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(OOApplication.getString(R.string.base_url)) // Base URL 설정
            .addConverterFactory(gsonConverterFactory) // GsonConverterFactory 추가
            .client(client) // OkHttpClient 추가
            .build()
    }

    @Provides
    @Singleton
    fun provideLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java) // LoginService 인터페이스 생성
    }
}
