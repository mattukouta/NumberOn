package kouta.numberon.Presenter

class CallResultPresenter {

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
}