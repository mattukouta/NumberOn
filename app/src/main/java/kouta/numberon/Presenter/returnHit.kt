package kouta.numberon.Presenter

fun returnHit(baseNumber : MutableList<Int?>, selectnumber : MutableList<Int?>) : Int {
    var hitCount = 0

    /**
     * Hitの数を計算している
     */
    for (n in 0 until baseNumber.size) {
        if (baseNumber[n] == selectnumber[n]) hitCount ++
    }

    return hitCount
}