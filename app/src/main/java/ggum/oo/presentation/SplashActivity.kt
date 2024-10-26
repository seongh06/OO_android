package ggum.oo.presentation

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Path
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import ggum.oo.R
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

        // dp를 픽셀로 변환하지 않고 직접 float 값으로 설정
        val startX = 120f // 시작 X
        val startY = 950f // 시작 Y
        val endX = 770f // 도착 X
        val radius = (endX- startX) / 2 // 반지름 설정

        // 경로 애니메이션 설정
        val path = Path().apply {
            // 반원 정의 (왼쪽으로 위쪽)
            arcTo(
                startX, startY - radius/8,  // 좌상단 (반원의 시작)
                endX, startY + radius, // 우하단 (반원의 끝)
                -180f,  // 시작 각도
                180f, // 각도 (180도 반원)
                false // 오프셋 여부
            )
        }

        // ObjectAnimator로 애니메이션 설정
        val animator = ObjectAnimator.ofFloat(logoImageView, "x", "y", path)
        animator.duration = 2000 // 애니메이션 지속 시간 (2초)
        animator.start()

        // 애니메이션 종료 후 다음 Activity로 이동
        animator.doOnEnd {
            startActivity(Intent(this, StartActivity::class.java))
            finish() // 현재 Activity 종료
        }
    }
}
