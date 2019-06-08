package kouta.numberon.Presenter.fragment

import kouta.numberon.Model.DataUtils

class OrderResultPresenter : OrderResultContract.Presenter {
    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPlayer1CardKey() : String {
        return DataUtils().PLAYER1_CARD
    }

    override fun getplayer2CardKey() : String {
        return DataUtils().PLAYER2_CARD
    }
}