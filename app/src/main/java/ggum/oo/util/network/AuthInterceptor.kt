package ggum.oo.util.network

import android.content.SharedPreferences
import ggum.oo.di.MyCookieJar
import okhttp3.Cookie
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val spf: SharedPreferences,
    private val cookieJar: MyCookieJar // MyCookieJar 주입
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // SharedPreferences에서 accessToken 가져오기
        val accessToken = spf.getString("jwt", null)

        // 로그 찍기
        Timber.d("현재 accessToken: $accessToken") // accessToken 로그

        // 요청 시 Authorization 헤더에 accessToken 추가
        val updatedRequest = if (!request.url.encodedPath.contains("/api/login") &&
            !request.url.encodedPath.contains("/api/signup") &&
            !accessToken.isNullOrEmpty()) {
            Timber.d("Authorization 헤더 추가됨: Bearer $accessToken") // 헤더 추가 로그
            request.newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
        } else {
            request
        }

        // 요청을 보내고 응답을 받음
        val response = chain.proceed(updatedRequest)

        // 응답이 성공적일 경우 accessToken을 헤더에서 저장
        if (response.isSuccessful) {
            val newAccessToken = response.header("accessToken")

            if (!newAccessToken.isNullOrEmpty()) {
                saveAccessToken(newAccessToken) // SharedPreferences에 저장
                Timber.d("새로운 accessToken 발급: $newAccessToken") // Timber로 로그 찍기
            } else {
                Timber.d("새로운 accessToken이 발급되지 않았습니다.")
            }
        } else {
            Timber.e("응답 실패: ${response.message}")
        }

        return response
    }

    private fun saveAccessToken(accessToken: String) {
        spf.edit().apply {
            putString("jwt", accessToken)
            apply()
        }
    }
}
