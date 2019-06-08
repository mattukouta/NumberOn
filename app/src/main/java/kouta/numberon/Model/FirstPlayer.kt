package kouta.numberon.Model

interface FirstPlayer {
    fun setFirstPlayer(firstPlayer : Int) {
        gameInfo.firstPlayer = firstPlayer
    }

    fun getFirstPlayer() : Int {
        return gameInfo.firstPlayer
    }
}