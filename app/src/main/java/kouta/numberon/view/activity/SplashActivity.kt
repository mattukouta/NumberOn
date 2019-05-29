package kouta.numberon.view.activity

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
import kouta.numberon.Presenter.DataUtils

import kouta.numberon.Presenter.AnimationAsync
import kouta.numberon.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent(this, SelectModeActivity::class.java)

//        /**
//         * 画面サイズの取得
//         *
//         * 初期化されることに気がついた(2019/05/15)
//         */
//        val wm : WindowManager = getSystemService(WINDOW_SERVICE) as WindowManager
//        val disp = wm.defaultDisplay
//        val size = Point()
//        disp.getSize(size)
//        DataUtils().WIN_WIDTH = size.y
//        DataUtils().WIN_HEIGHT = size.y

        Animation()

        /**
         * 画面タップでSelectModeActivityへ
         */
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
                Animation.RELATIVE_TO_SELF, - 0.5f)
        anim_up.duration = 800

        val anim_down = TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, - 0.5f,
                Animation.RELATIVE_TO_SELF, 0.0f)
        anim_down.duration = 800

        GlobalScope.launch(Dispatchers.Main) {
            while (true) {
                /**
                 * 上へ移動するアニメーションを実行
                 */
                title_text.AnimationAsync(anim_up)
                /**
                 * 下へ移動するアニメーションを実行
                 */
                title_text.AnimationAsync(anim_down)
            }
        }
    }
}
