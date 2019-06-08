package kouta.numberon.Presenter.activity

import kouta.numberon.Model.gameInfo
import kouta.numberon.Presenter.Contract.SelectModeContract

class SelectModePresenter : SelectModeContract.Presenter{
    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setMode(mode : String) {
        gameInfo.gameMode = mode
    }
}