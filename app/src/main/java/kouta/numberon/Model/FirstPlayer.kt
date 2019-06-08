package kouta.numberon.Model

import kouta.numberon.Model.gameInfo

interface FirstPlayer {
    fun setFirstPlayer(firstPlayer : Int) {
        gameInfo.firstPlayer = firstPlayer
    }

    fun getFirstPlayer() : Int {
        return gameInfo.firstPlayer
    }
}