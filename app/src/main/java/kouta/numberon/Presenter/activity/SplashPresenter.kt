package kouta.numberon.Presenter.activity

import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import kouta.numberon.Presenter.Contract.SplashContract

class SplashPresenter(private val view : SplashContract.View) : SplashContract.Presenter {

    override fun anim_up() : Animation {
        val anim = TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, - 0.5f)
        anim.duration = 800
        return anim
    }

    override fun anim_down() : Animation {
        val anim = TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, - 0.5f,
                Animation.RELATIVE_TO_SELF, 0.0f)
        anim.duration = 800
        return anim
    }

    override fun start() {
        view.showAnimation()
    }
}