package kouta.numberon.Presenter.fragment

import kouta.numberon.Model.FirstPlayer
import kouta.numberon.Model.Mode
import kouta.numberon.Presenter.BasePresenter
import kouta.numberon.Presenter.BaseView
import kouta.numberon.Presenter.NumberToCard

interface OrderResultContract {
    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter, NumberToCard, FirstPlayer, Mode {
        fun getPlayer1CardKey() : String
        fun getPlayer2CardKey() : String
    }
}