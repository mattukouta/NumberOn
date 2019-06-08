package kouta.numberon.Model

import kouta.numberon.Model.gameInfo

interface Mode {
    fun setMode(mode : String) {
        gameInfo.gameMode = mode
    }

    fun getMode() : String {
        return gameInfo.gameMode
    }
}