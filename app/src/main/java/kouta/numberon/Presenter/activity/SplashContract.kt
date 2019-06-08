package kouta.numberon.Presenter.activity

import android.view.animation.Animation
import kouta.numberon.Presenter.BasePresenter
import kouta.numberon.Presenter.BaseView

interface SplashContract {
    interface View : BaseView<Presenter> {
        fun showAnimation()
    }

    interface Presenter : BasePresenter {
        fun anim_up() : Animation
        fun anim_down() : Animation
    }
}