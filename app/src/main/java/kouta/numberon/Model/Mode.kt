package kouta.numberon.Model


interface Mode {
    fun setMode(mode : String) {
        gameInfo.gameMode = mode
    }

    fun getMode() : String {
        return gameInfo.gameMode
    }
}