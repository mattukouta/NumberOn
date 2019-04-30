package kouta.numberon.view.activity

import android.content.Context
import android.content.Intent
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.TranslateAnimation

import kotlinx.android.synthetic.main.activity_splash.*

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kouta.numberon.Model.DataUtils

import kouta.numberon.Presenter.AnimationAsync
import kouta.numberon.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent(this, SelectModeActivity::class.java)

        /**
         * 画面サイズの取得
         */
        val wm : WindowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val disp = wm.getDefaultDisplay()
        val size = Point()
        disp.getSize(size)
        DataUtils().WIN_WIDTH = size.x
        DataUtils().WIN_HEIGHT = size.y

        Animation()

        background.setOnClickListener {
            startActivity(intent)
            overridePendingTransition(0, 0)
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
                title_text.AnimationAsync(anim_down) // 下へ移動するアニzメーションを実行
            }
        }
    }
}
