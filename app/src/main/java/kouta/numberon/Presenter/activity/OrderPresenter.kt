package kouta.numberon.Presenter.activity

import kouta.numberon.Model.gameInfo
import kouta.numberon.Presenter.Contract.OrderContract

class OrderPresenter : OrderContract.Presenter {
    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMode() : String {
        return gameInfo.gameMode
    }
}