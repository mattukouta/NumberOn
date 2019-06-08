package kouta.numberon.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation

import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import kouta.numberon.Presenter.activity.SplashContract
import kouta.numberon.Presenter.activity.SplashPresenter
import kouta.numberon.R
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SplashActivity : AppCompatActivity(), SplashContract.View {
    override lateinit var presenter : SplashContract.Presenter
    lateinit var anim_up : Animation
    lateinit var anim_down : Animation

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent(this, SelectModeActivity::class.java)

        presenter = SplashPresenter(this)

        anim_up = presenter.anim_up()
        anim_down = presenter.anim_down()

        presenter.start()

        /**
         * 画面タップでSelectModeActivityへ
         */
        background.setOnClickListener {
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }

    override fun showAnimation() {
        GlobalScope.launch(Dispatchers.Main) {
            while (true) {
                title_text.AnimationAsync(anim_up)

                title_text.AnimationAsync(anim_down)
            }
        }
    }

    suspend fun View.AnimationAsync(anim : Animation) {
        return suspendCoroutine { continuation ->
            anim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation : Animation?) {
                }

                override fun onAnimationEnd(animation : Animation?) {
                    continuation.resume(Unit)
                }

                override fun onAnimationRepeat(animation : Animation?) {
                }
            })
            this.startAnimation(anim)
        }
    }
}
