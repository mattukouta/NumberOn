package kouta.numberon.Presenter.Contract

import kouta.numberon.FirstPlayer
import kouta.numberon.Presenter.BasePresenter
import kouta.numberon.Presenter.BaseView
import kouta.numberon.Presenter.NumberToCard

interface OrderResultContract {
    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter, NumberToCard, FirstPlayer {

    }
}