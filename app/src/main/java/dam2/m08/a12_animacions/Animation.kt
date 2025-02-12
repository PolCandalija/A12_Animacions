package dam2.m08.a12_animacions

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.doOnPreDraw
import dam2.m08.a12_animacions.databinding.ActivityAnimationBinding
import dam2.m08.a12_animacions.databinding.ActivityMainBinding
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlin.time.Duration

class Animation : AppCompatActivity() {
    lateinit var binding: ActivityAnimationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnimationBinding.inflate(layoutInflater)

        setContentView(binding.root)

        var animatorSet = AnimatorSet()

        binding.main.doOnPreDraw {
            startAnimation(animatorSet)
        }

        binding.pilota.setOnClickListener{
            if (!animatorSet.isRunning)
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
    private fun startAnimation(animatorSet: AnimatorSet) {
        val startY = binding.pilota.y

        val bottomLeftY = binding.main.bottom - binding.pilota.height.toFloat()

        val bottomRightX = binding.main.right - binding.pilota.width.toFloat()

        val topRightY = startY

        val topCenterX = (binding.main.right - binding.main.left) / 2f - binding.pilota.width / 2f

        val centerY = (binding.main.bottom - binding.main.top) / 2f - binding.pilota.height / 2f

        val moveToBottomLeft = AnimatorSet()
        moveToBottomLeft.playTogether(
            ObjectAnimator.ofFloat(binding.pilota, "rotation", 180f),
            ObjectAnimator.ofFloat(binding.pilota, "translationY", bottomLeftY)
        )

        val moveToBottomRight = AnimatorSet()
        moveToBottomRight.playTogether(
            ObjectAnimator.ofFloat(binding.pilota, "rotation", 360f),
            ObjectAnimator.ofFloat(binding.pilota, "translationX", bottomRightX)
        )

        val moveToTopRight = AnimatorSet()
        moveToTopRight.playTogether(
            ObjectAnimator.ofFloat(binding.pilota, "rotation", 540f),
            ObjectAnimator.ofFloat(binding.pilota, "translationY", topRightY)
        )

        val moveToTopCenter = AnimatorSet()
        moveToTopCenter.playTogether(
            ObjectAnimator.ofFloat(binding.pilota, "rotation", 630f),
            ObjectAnimator.ofFloat(binding.pilota, "translationX", topCenterX)
        )

        val moveToCenter = AnimatorSet()
        moveToCenter.playTogether(
            ObjectAnimator.ofFloat(binding.pilota, "rotation", 720f),
            ObjectAnimator.ofFloat(binding.pilota, "translationY", centerY)
        )

        animatorSet.playSequentially(
            moveToBottomLeft,
            moveToBottomRight,
            moveToTopRight,
            moveToTopCenter,
            moveToCenter
        )
        animatorSet.duration = 1000
        animatorSet.start()
    }

}