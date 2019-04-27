package kouta.numberon.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.TranslateAnimation

import kotlinx.android.synthetic.main.activity_splash.*

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import kouta.numberon.Presenter.AnimationAsync
import kouta.numberon.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Animation()

        val intent = Intent(this, SelectMode::class.java)

        background.setOnClickListener {
            startActivity(intent)
        }
    }

    /**
     * Animationの適用
     */
    fun Animation() {
        val anim_up = TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -0.5f)
        anim_up.duration = 800

        val anim_down = TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -0.5f,
                Animation.RELATIVE_TO_SELF, 0.0f)
        anim_down.duration = 800

        GlobalScope.launch(Dispatchers.Main) {
            while (true) {
                title_text.AnimationAsync(anim_up) // 上へ移動するアニメーションを実行
                title_text.AnimationAsync(anim_down) // 下へ移動するアニメーションを実行
            }
        }
    }
}
