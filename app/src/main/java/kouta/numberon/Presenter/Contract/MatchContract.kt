package kouta.numberon.Presenter.Contract

import kouta.numberon.Digit
import kouta.numberon.Mode
import kouta.numberon.Presenter.BasePresenter
import kouta.numberon.Presenter.BaseView
import kouta.numberon.Presenter.ModeTextChange
import kouta.numberon.Presenter.NumberToCard

interface MatchContract {
    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter, NumberToCard, ModeTextChange, Mode, Digit {
        fun numberToSum(digit_NTS : Int, number_NTS : MutableList<Int?>) : String
        fun returnBlow(baseNumber : MutableList<Int?>, callNumber : MutableList<Int?>) : Int
        fun returnHit(baseNumber : MutableList<Int?>, selectnumber : MutableList<Int?>) : Int
    }
}