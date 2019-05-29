package kouta.numberon.Presenter.activity

import kouta.numberon.Model.gameInfo

class SelectModePresenter {
    fun setMode(mode : String) {
        gameInfo.gameMode = mode
    }
}