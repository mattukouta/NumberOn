package kouta.numberon.Presenter.fragment

import kouta.numberon.Model.DataUtils
import kouta.numberon.R

class OrderResultPresenter : OrderResultContract.Presenter {


    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPlayer1CardKey() : String {
        return DataUtils().PLAYER1_CARD
    }

    override fun getPlayer2CardKey() : String {
        return DataUtils().PLAYER2_CARD
    }

    override fun OrderResultTextBranch(mode : String, player1 : Int, player2 : Int) : Int {
        var string = 0

        if (mode == "cpu") {
            if (player1 > player2) {
                string = R.string.order_player
            } else {
                string = R.string.order_cpu
                setFirstPlayer(2)
            }
        } else if (mode == "local") {
            if (player1 > player2) {
                string = R.string.order_player1
            } else {
                string = R.string.order_player2
                setFirstPlayer(2)
            }
        }
        return string
    }
}