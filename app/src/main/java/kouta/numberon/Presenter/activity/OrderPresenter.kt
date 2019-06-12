package kouta.numberon.Presenter.activity

import kouta.numberon.Model.DataUtils

class OrderPresenter : OrderContract.Presenter {
    override fun start() {}

    override fun getDigitRequestCode() : Int {
        return DataUtils().DIGIT_REQUEST_CODE
    }

    override fun getPlayer1CardKey() : String {
        return DataUtils().PLAYER1_CARD
    }

    override fun getPlayer2CardKey() : String {
        return DataUtils().PLAYER2_CARD
    }

    override fun getListRemoveShuffle(selectNumber : Int) : Int {
        var numbers = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        numbers.removeAll { it == selectNumber}
        numbers.shuffled()
        return numbers.random()
    }
}