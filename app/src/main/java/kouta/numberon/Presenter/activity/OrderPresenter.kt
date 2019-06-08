package kouta.numberon.Presenter.activity

import kouta.numberon.Model.DataUtils

class OrderPresenter : OrderContract.Presenter {
    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDigitRequestCode() : Int {
        return DataUtils().DIGIT_REQUEST_CODE
    }

    override fun getPlayer1CardKey() : String {
        return DataUtils().PLAYER1_CARD
    }

    override fun getPlayer2CardKey() : String {
        return DataUtils().PLAYER2_CARD
    }
}