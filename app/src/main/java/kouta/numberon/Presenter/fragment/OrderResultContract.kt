package kouta.numberon.Presenter.fragment

import kouta.numberon.Model.FirstPlayer
import kouta.numberon.Presenter.BasePresenter
import kouta.numberon.Presenter.BaseView
import kouta.numberon.Presenter.NumberToCard

interface OrderResultContract {
    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter, NumberToCard, FirstPlayer {
        fun getPlayer1CardKey() : String
        fun getplayer2CardKey() : String
    }
}