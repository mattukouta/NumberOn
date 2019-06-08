package kouta.numberon

import kouta.numberon.Model.gameInfo

interface Digit {
    fun setDigit(digit : Int) {
        gameInfo.gameDigit = digit
    }

    fun getDigit() : Int {
        return gameInfo.gameDigit
    }
}