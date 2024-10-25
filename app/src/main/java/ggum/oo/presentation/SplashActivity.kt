package ggum.oo.presentation

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Path
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import ggum.oo.R
import ggum.oo.presentation.login.LoginActivity
import ggum.oo.presentation.login.StartActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var logoImageView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        logoImageView = findViewById(R.id.iv_logo_circle)

        // 애니메이션 실행
        startLogoAnimation()
    }

    private fun startLogoAnimation() {
        // 페이드 인 효과 추가
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = 1000 // 1초 동안 페이드 인
        logoImageView.startAnimation(fadeIn) // 페이드 인 애니메이션 시작

        // dp를 픽셀로 변환
        val startX = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50f, resources.displayMetrics)
        val startY = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 360f, resources.displayMetrics)
        val endX = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 282f, resources.displayMetrics)
        val endY = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 396f, resources.displayMetrics)

        // 원형 경로 애니메이션 설정
        val path = Path().apply {
            // 시작점과 끝점을 기반으로 원형 경로 정의
            moveTo(startX, startY) // 시작점
            arcTo(endX, endY, startX, startY, 0f, 360f, false) // 원호 정의
        }

        // ObjectAnimator로 애니메이션 설정
        val animator = ObjectAnimator.ofFloat(logoImageView, "translationX", "translationY", path)
        animator.duration = 2000 // 애니메이션 지속 시간 (2초)
        animator.start()

        // 애니메이션 종료 후 다음 Activity로 이동
        animator.doOnEnd {
            startActivity(Intent(this, StartActivity::class.java))
            finish() // 현재 Activity 종료
        }
    }
}
