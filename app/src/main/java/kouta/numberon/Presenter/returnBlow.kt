package kouta.numberon.Presenter

fun returnBlow(baseNumber : MutableList<Int?>, callNumber : MutableList<Int?>) : Int {
    var blowCount = 0

    /**
     * Blowの数を計算
     * base_number[n] != call_number[n]でHit分を除いている
     */
    for (n in 0 until baseNumber.size) {
        if (baseNumber[n] != callNumber[n]) {
            for (i in 0 until callNumber.size) {
                if (baseNumber[n] == callNumber[i]) blowCount ++
            }
        }
    }

    return blowCount
}