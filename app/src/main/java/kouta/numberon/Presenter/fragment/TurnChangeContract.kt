package kouta.numberon.Presenter.fragment

import kouta.numberon.Model.FirstPlayer
import kouta.numberon.Model.Mode
import kouta.numberon.Presenter.BasePresenter
import kouta.numberon.Presenter.BaseView

interface TurnChangeContract {
    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter, Mode, FirstPlayer
}