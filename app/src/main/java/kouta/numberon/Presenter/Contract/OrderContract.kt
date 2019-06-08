package kouta.numberon.Presenter.Contract

import kouta.numberon.Presenter.BasePresenter
import kouta.numberon.Presenter.BaseView
import kouta.numberon.Presenter.ModeTextChange

interface OrderContract {
    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter, ModeTextChange {
        fun getMode() : String
    }
}