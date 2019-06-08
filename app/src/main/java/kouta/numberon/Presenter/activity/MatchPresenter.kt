package kouta.numberon.Presenter.activity

import kouta.numberon.R

class MatchPresenter : MatchContract.Presenter {
    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
}