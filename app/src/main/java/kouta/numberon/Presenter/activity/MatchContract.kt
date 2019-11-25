package kouta.numberon.Presenter.activity

import kouta.numberon.Model.Digit
import kouta.numberon.Model.FirstPlayer
import kouta.numberon.Model.Mode
import kouta.numberon.Presenter.BasePresenter
import kouta.numberon.Presenter.BaseView
import kouta.numberon.Presenter.ModeTextChange
import kouta.numberon.Presenter.NumberToCard

interface MatchContract {
    interface View : BaseView<Presenter> {
        fun showGameResult(state_C : Int)
        fun showCallList(result : String, sum_C : String)
        fun radioInit()
        fun showTurnChecngeFragment(result : String, state : Int)
    }

    interface Presenter : BasePresenter, NumberToCard, ModeTextChange, Mode, Digit, FirstPlayer {
        fun numberToSum(digit_NTS : Int, number_NTS : MutableList<Int?>) : String
        fun returnBlow(baseNumber : MutableList<Int?>, callNumber : MutableList<Int?>) : Int
        fun returnHit(baseNumber : MutableList<Int?>, selectnumber : MutableList<Int?>) : Int
        fun returnFirstText(player : Int, mode : String) : Int
        fun returnSecondText(player : Int, mode : String) : Int
        fun returnFirstTurnText(player : Int, mode : String) : Int
        fun returnSecondTurnText(player : Int, mode : String) : Int
        fun getWinPlayer(state : Int, firstPlayer : Int, mode : String) : String
        fun createDigitList(digit : Int) : Int
        fun checkList(number : Int, digit : Int) : Boolean
        var cpuNumber : MutableList<Int>
        fun returnIntToList(hoge : Int) : MutableList<Int?>
        //        fun cpuSelectNumber() : MutableList<Int?>
        fun cpuBaseNumber() : MutableList<Int?>

        fun removeList(call_number_C : MutableList<Int?>, hit : Int, blow : Int)
        fun stateChenge(state : Int) : Int
        fun callListCheck(call_number : MutableList<Int?>) : Boolean
    }
}