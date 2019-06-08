package kouta.numberon.Presenter.activity

import kouta.numberon.Model.gameInfo
import kouta.numberon.Presenter.Contract.MatchContract

class MatchPresenter : MatchContract.Presenter {
    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getMode() : String {
        return gameInfo.gameMode
    }

    fun getDigit() : Int {
        return gameInfo.gameDigit
    }

    fun getFirstPlayer() : Int {
        return gameInfo.firstPlayer
    }

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

    override fun numberToSum(digit_NTS : Int, number_NTS : MutableList<Int?>) : String {
        /**
         * ここで配列numberを数字に変換する。
         */
        var index = 10.0
        var digit_number = 0
        index = Math.pow(index, (digit_NTS - 1).toDouble())

        for (n in number_NTS) {
            if (n != null) {
                digit_number += n * index.toInt()
                index /= 10
            }
        }

        return String.format("%0${digit_NTS}d", digit_number)
    }
}