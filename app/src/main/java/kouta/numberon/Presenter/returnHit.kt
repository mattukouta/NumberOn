package kouta.numberon.Presenter

fun returnHit(baseBumber : MutableList<Int?>, selectnumber : MutableList<Int?>) : Int {
    var hitCount = 0

    for (n in 0 until baseBumber.size) {
        if (baseBumber[n] == selectnumber[n]) hitCount ++
    }

    return hitCount
}