package kouta.numberon.Presenter.activity

import android.view.View
import android.view.animation.Animation
import kouta.numberon.Presenter.BasePresenter
import kouta.numberon.Presenter.BaseView

interface SplashContract {
    interface View : BaseView<Presenter> {
        fun showAnimation()
        suspend fun android.view.View.AnimationAsync(anim : Animation)
    }

    interface Presenter : BasePresenter {
        fun anim_up() : Animation
        fun anim_down() : Animation
    }
}