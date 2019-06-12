package kouta.numberon.Presenter.activity

import kouta.numberon.Model.Mode
import kouta.numberon.Presenter.BasePresenter
import kouta.numberon.Presenter.BaseView
import kouta.numberon.Presenter.ModeTextChange

interface OrderContract {
    interface View : BaseView<Presenter> {
        fun showResultFragment()
        fun showCardColor(v : android.view.View?)
        fun showDigitFragment()
    }

    interface Presenter : BasePresenter, ModeTextChange, Mode {

        fun getDigitRequestCode() : Int
        fun getPlayer1CardKey() : String
        fun getPlayer2CardKey() : String
        fun getListRemoveShuffle(selectNumber : Int) : Int
    }
}