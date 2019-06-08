package kouta.numberon

import kouta.numberon.Model.gameInfo

interface FirstPlayer {
    fun setFirstPlayer(firstPlayer : Int) {
        gameInfo.firstPlayer = firstPlayer
    }

    fun getFirstPlayer() : Int {
        return gameInfo.firstPlayer
    }
}