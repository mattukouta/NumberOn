package kouta.numberon.Presenter

fun returnBlow(base_number : MutableList<Int?>, call_number : MutableList<Int?>) : Int {
    var blowCount = 0

    for (n in 0 until base_number.size) {
        if (base_number[n] != call_number[n]) {
            for (i in 0 until call_number.size) {
                if (base_number[n] == call_number[i]) blowCount ++
            }
        }
    }

    return blowCount
}