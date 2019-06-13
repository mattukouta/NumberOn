package kouta.numberon.Presenter.fragment

import kouta.numberon.Presenter.BasePresenter
import kouta.numberon.Presenter.BaseView

interface GameResultContract {
    interface View : BaseView<Presenter> {
        fun intent()
    }

    interface Presenter : BasePresenter
}