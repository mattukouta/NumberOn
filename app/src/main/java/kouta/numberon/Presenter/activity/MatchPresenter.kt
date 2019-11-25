package kouta.numberon.Presenter.activity

import android.util.Log
import kouta.numberon.R
import kotlin.math.pow

class MatchPresenter(private val view : MatchContract.View) : MatchContract.Presenter {

    override var cpuNumber = mutableListOf<Int>()

    override fun start() {
//        view.cpuBaseNumber()
    }

    override fun returnHit(baseNumber : MutableList<Int?>, selectnumber : MutableList<Int?>) : Int {
        var hitCount = 0

        /**
         * Hitの数を計算している
         */
        for (n in 0 until baseNumber.size) {
            if (baseNumber[n] == selectnumber[n]) hitCount ++
        }

        return hitCount
    }

    override fun returnBlow(baseNumber : MutableList<Int?>, callNumber : MutableList<Int?>) : Int {
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

    override fun returnFirstText(player : Int, mode : String) : Int {
        var text = 0

        if (mode == "cpu") {

            if (player == 1) {
                text = R.string.player_select
            } else if (player == 2) {
                text = R.string.cpu_select
            }

        } else if (mode == "local") {
            if (player == 1) {
                text = R.string.player1_select
            } else if (player == 2) {
                text = R.string.player2_select
            }
        }

        return text
    }

    override fun returnSecondText(player : Int, mode : String) : Int {
        var text = 0

        if (mode == "cpu") {

            if (player == 1) {
                text = R.string.cpu_select
            } else if (player == 2) {
                text = R.string.player_select
            }

        } else if (mode == "local") {
            if (player == 1) {
                text = R.string.player2_select
            } else if (player == 2) {
                text = R.string.player1_select
            }
        }

        return text
    }

    override fun returnFirstTurnText(player : Int, mode : String) : Int {
        var text = 0

        if (mode == "cpu") {

            if (player == 1) {
                text = R.string.player_turn
            } else if (player == 2) {
                text = R.string.cpu_turn
            }

        } else if (mode == "local") {
            if (player == 1) {
                text = R.string.player1_turn
            } else if (player == 2) {
                text = R.string.player2_turn
            }
        }

        return text
    }

    override fun returnSecondTurnText(player : Int, mode : String) : Int {
        var text = 0

        if (mode == "cpu") {

            if (player == 1) {
                text = R.string.cpu_turn
            } else if (player == 2) {
                text = R.string.player_turn
            }

        } else if (mode == "local") {
            if (player == 1) {
                text = R.string.player2_turn
            } else if (player == 2) {
                text = R.string.player1_turn
            }
        }

        return text
    }

    override fun getWinPlayer(state : Int, firstPlayer : Int, mode : String) : String {
        lateinit var player : String

        if (mode == "cpu") {
            if (state == 3) {
                if (firstPlayer == 1) {
                    player = "Player"
                } else if (firstPlayer == 2) {
                    player = "CPU"
                }
            } else if (state == 4) {
                if (firstPlayer == 1) {
                    player = "CPU"
                } else if (firstPlayer == 2) {
                    player = "Player"
                }
            }
        } else if (mode == "local") {
            if (state == 3) {
                if (firstPlayer == 1) {
                    player = "Player1"
                } else if (firstPlayer == 2) {
                    player = "Player2"
                }
            } else if (state == 4) {
                if (firstPlayer == 1) {
                    player = "Player2"
                } else if (firstPlayer == 2) {
                    player = "Player1"
                }
            }
        }

        return player
    }

    override fun createDigitList(digit : Int) : Int {
        var min_count = 0
        var max_count = 9
        var min_number = 0
        var max_number = 0
        var list = mutableListOf<Int>()

        for (n in 0 until digit) {
            min_number += min_count * (10f.pow(digit - 1 - n)).toInt()
            min_count += 1
        }

        for (n in 0 until digit) {
            max_number += max_count * (10f.pow(digit - 1 - n)).toInt()
            max_count -= 1
        }

        for (n in min_number..max_number) {

            if (checkList(n, digit)) {
                list.add(n)
            }
        }

        cpuNumber = list

        return list.random()
    }

    // 魔の関数になってる
    override fun checkList(number : Int, digit : Int) : Boolean {
        var list = mutableListOf(11, 12, 13, 14, 15)

        when (digit) {
            2 -> {
                list[3] = number / 10
                list[4] = number % 10
            }
            3 -> {
                list[2] = number / 100
                list[3] = (number - list[2] * 100) / 10
                list[4] = (number - list[2] * 100) % 10
            }
            4 -> {
                list[1] = number / 1000
                list[2] = (number - list[1] * 1000) / 100
                list[3] = (number - list[1] * 1000 - list[2] * 100) / 10
                list[4] = (number - list[1] * 1000 - list[2] * 100) % 10
            }
            5 -> {
                list[0] = number / 10000
                list[1] = (number - list[0] * 10000) / 1000
                list[2] = (number - list[0] * 10000 - list[1] * 1000) / 100
                list[3] = (number - list[0] * 10000 - list[1] * 1000 - list[2] * 100) / 10
                list[4] = (number - list[0] * 10000 - list[1] * 1000 - list[2] * 100) % 10
            }
        }

        if ((list.distinct()).size == 5) {
            return true
        }

        return false
    }

    /**
     * 与えられた引数のIntをListとして返す関数
     */
    override fun returnIntToList(hoge : Int) : MutableList<Int?> {
        val digit = getDigit()
        var hogelist = mutableListOf<Int?>()
        for (n in 0 until digit) {
            if (n == 0) {
                hogelist.add(hoge / (10f.pow(digit - 1)).toInt())
            } else {
                var sum = 0
                for (i in 0 until n) {
                    sum += hogelist[i]?.times(((10f.pow(digit - 1 - i)).toInt())) ?: 0
                }
                hogelist.add((hoge - sum) / (10f.pow(digit - 1 - n)).toInt())
            }
        }
        return hogelist
    }

    /**
     * 桁に応じたlistを作成し、
     * 作成したリストからrandomに取り出し実行
     */
    override fun cpuBaseNumber() : MutableList<Int?> {
        val digit = getDigit()
        val cpu_number = createDigitList(digit)

        return returnIntToList(cpu_number)
    }

    /**
     * cpu対戦時、cpuが宣言したnumberとhit&blowの結果から、
     * 条件にあったnumberをリストから削除する
     */
    override fun removeList(call_number_C : MutableList<Int?>, hit : Int, blow : Int) {
        val hogehoge = mutableListOf<Int>()
        var hoge = mutableListOf<Int?>()
        val digit = getDigit()

        for (n in 0 until cpuNumber.size) {
            hogehoge.add(cpuNumber[n])
        }

        for (n in 0 until digit) {
            hoge.add(0)
        }
        for (x in 0 until hogehoge.size) {

            hoge = returnIntToList(hogehoge[x])

            val removeHit = returnHit(call_number_C, hoge)
            val removeBlow = returnBlow(call_number_C, hoge)
            if (hit != removeHit || blow != removeBlow) {
                cpuNumber.removeAll { it == hogehoge[x] }
            }
        }
    }

    override fun stateChenge(state : Int) : Int {
        var returnState = state
        if (state == 4) {
            returnState -= 1
        } else {
            returnState += 1
        }
        return returnState
    }

    override fun callListCheck(call_number : MutableList<Int?>) : Boolean {
        /**
         * 作成していたnumberリストの全ての値がnullではないことの確認
         * number.filterNotNull()でnullじゃない要素の抽出
         * number.filterNotNull().size == digitでnullじゃない要素のサイズと、
         * 選択していた桁数が等しいことを確認し次の処理へ
         */

        if (call_number.filterNotNull().size == getDigit()) {

            Log.d("check", "good!")

            return true

        } else {
            Log.d("check", "bad..")

            return false

        }
    }

//    /**
//     * listからrandomに取り出し実行
//     */
//    // 関数内で関数の使用
//    override fun cpuSelectNumber() : MutableList<Int?> {
//        val selectNumber = cpuNumber.random()
//
//        return returnIntToList(selectNumber)
//    }
}