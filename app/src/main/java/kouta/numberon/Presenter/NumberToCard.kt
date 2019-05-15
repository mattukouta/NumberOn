package kouta.numberon.Presenter

import kouta.numberon.R

/**
 * idから画像intの変換用
 */
fun NumberToCard(number : Int) : Int {
    when (number) {
        0 -> return R.drawable.trump_0
        1 -> return R.drawable.trump_1
        2 -> return R.drawable.trump_2
        3 -> return R.drawable.trump_3
        4 -> return R.drawable.trump_4
        5 -> return R.drawable.trump_5
        6 -> return R.drawable.trump_6
        7 -> return R.drawable.trump_7
        8 -> return R.drawable.trump_8
        9 -> return R.drawable.trump_9
    }
    return 0
}