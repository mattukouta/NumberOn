package kouta.numberon.Presenter.activity

import kouta.numberon.Model.gameInfo

class OrderPresenter {
    fun getMode() : String {
        return gameInfo.gameMode
    }
}