package ggum.oo

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.annotation.StringRes
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.auth0.android.jwt.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import ggum.oo.util.network.NetworkConnectionChecker
import timber.log.Timber

// @HiltAndroidApp : Hilt 사용시 반드시 선행 되어야 하는 부분, 모든 의존성 주입의 시작점
@HiltAndroidApp
class OOApplication : Application(), DefaultLifecycleObserver {
    override fun onCreate() {
        super<Application>.onCreate()
        Timber.plant(Timber.DebugTree())
        context = applicationContext
        networkConnectionChecker = NetworkConnectionChecker(context)
    }

    override fun onStop(owner: LifecycleOwner) {
        networkConnectionChecker.unregister()
        super.onStop(owner)
    }

    override fun onStart(owner: LifecycleOwner) {
        networkConnectionChecker.register()
        super.onStart(owner)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var context: Context

        fun getString(@StringRes stringResId: Int): String {
            return context.getString(stringResId)
        }

        private lateinit var networkConnectionChecker: NetworkConnectionChecker
        fun isOnline() = networkConnectionChecker.isOnline()
    }
}