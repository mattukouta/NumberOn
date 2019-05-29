package kouta.numberon.Presenter.fragment

import kouta.numberon.Model.gameInfo

class OrderResultPresenter {
    fun getDigit() : Int {
        return gameInfo.gameDigit
    }

    fun setfirstPlayer(player : Int) {
        gameInfo.firstPlayer = player
    }
}