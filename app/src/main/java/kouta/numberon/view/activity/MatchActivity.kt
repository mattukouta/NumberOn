package kouta.numberon.view.activity

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_match.*
import kouta.numberon.Model.Player
import kouta.numberon.Presenter.activity.MatchContract
import kouta.numberon.Presenter.activity.MatchPresenter
import kouta.numberon.R
import kouta.numberon.view.Adapter.CallListAdapter
import kouta.numberon.view.Fragment.GameResultFragment
import kouta.numberon.view.Fragment.TurnChangeFragment
import kotlin.math.pow
import kotlin.math.sign

// radioButton生成部分が'number.add(null)', 'number[n - 1] = null'以外同じ ←activity内にメソッドの作成
class MatchActivity : AppCompatActivity(), View.OnClickListener, MatchContract.View {
    override lateinit var presenter : MatchContract.Presenter

    var firstPlayer = 0
    var digit = 0
    /**
     * 後述はしてあるが、
     * state = 1　は先攻の人のNumber設定
     * state = 2　は後攻の人のNumber設定
     * state = 3　は先攻の人のNumber選択
     * state = 4　は後攻の人のNumber選択
     */
    var state = 1
    var number = mutableListOf<Int?>()
    var flag = 0

    var list = ArrayList<Player>()
    lateinit var player_result : Player

    var player1_setting_number = mutableListOf<Int?>()
    var player2_setting_number = mutableListOf<Int?>()

    lateinit var first_turn_text : String
    lateinit var second_turn_text : String
    lateinit var mode : String

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        presenter = MatchPresenter(this)
        firstPlayer = presenter.getFirstPlayer()
        digit = presenter.getDigit()

        mode = presenter.getMode()
        select_title.setText(presenter.modeTextChange(mode))

        for (n in 1..digit) {
            /**
             * radiobuttonの追加
             */
            val radio = RadioButton(this)
            radio.background = resources.getDrawable(R.drawable.card_checked, null)
            radio.setButtonDrawable(R.drawable.trump_white)
            radio.id = n
            radioGroup.addView(radio)

            /**
             * 配列の作成
             */
            number.add(null)
        }
        first_turn_text = resources.getString(presenter.returnFirstText(firstPlayer, mode))
        second_turn_text = resources.getString(presenter.returnSecondText(firstPlayer, mode))

        turn_text.text = first_turn_text

        if (mode == "cpu" && firstPlayer == 2) {
            cpuBaseNumber()
        }

        /**
         * ボタン押すための前処理
         */
        btn_0.setOnClickListener(this)
        btn_1.setOnClickListener(this)
        btn_2.setOnClickListener(this)
        btn_3.setOnClickListener(this)
        btn_4.setOnClickListener(this)
        btn_5.setOnClickListener(this)
        btn_6.setOnClickListener(this)
        btn_7.setOnClickListener(this)
        btn_8.setOnClickListener(this)
        btn_9.setOnClickListener(this)
        btn_call.setOnClickListener(this)
    }

    /**
     * バックキーの処理
     */
    override fun onBackPressed() {
        /**
         * activityのスタックを全て削除してSelectModeActivityに遷移する
         */
        val intent = Intent(this, SelectModeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    /**
     * 画面下の数字およびcallボタン選択時の処理
     */
    override fun onClick(v : View?) {
        var select_card = 0
        var select_number = 0
        var number_flag = 0
        when (v) {
            btn_0 -> {
                select_number = 0
                select_card = presenter.numberToCard(select_number)
            }
            btn_1 -> {
                select_number = 1
                select_card = presenter.numberToCard(select_number)
            }
            btn_2 -> {
                select_number = 2
                select_card = presenter.numberToCard(select_number)
            }
            btn_3 -> {
                select_number = 3
                select_card = presenter.numberToCard(select_number)
            }
            btn_4 -> {
                select_number = 4
                select_card = presenter.numberToCard(select_number)
            }
            btn_5 -> {
                select_number = 5
                select_card = presenter.numberToCard(select_number)
            }
            btn_6 -> {
                select_number = 6
                select_card = presenter.numberToCard(select_number)
            }
            btn_7 -> {
                select_number = 7
                select_card = presenter.numberToCard(select_number)
            }
            btn_8 -> {
                select_number = 8
                select_card = presenter.numberToCard(select_number)
            }
            btn_9 -> {
                select_number = 9
                select_card = presenter.numberToCard(select_number)
            }
            btn_call -> {
                call()
            }
        }

        for (n in number) {
            if (n == select_number) {
                number_flag = 1
            }
        }

        /**
         * 画面下の選択した数字を選択していたradioButtonに画面に差し込み
         */

        if (radioGroup.checkedRadioButtonId != - 1 && v != btn_call && number_flag != 1) {
            val radio = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            radio.setButtonDrawable(select_card)
            number[radioGroup.checkedRadioButtonId - 1] = select_number
        }
    }

    override fun cpuBaseNumber() {
        val cpu_number = presenter.createDigitList(digit)
        for (n in 0 until digit) {
            if (n == 0) {
                number[n] = cpu_number / (10f.pow(digit - 1)).toInt()
            } else {
                var sum = 0
                for (i in 0 until n) {
                    sum += number[i]?.times(((10f.pow(digit - 1 - i)).toInt())) ?: 0
                }
                number[n] = (cpu_number - sum) / (10f.pow(digit - 1 - n)).toInt()
            }
        }
        Log.d("checknumber", "$number : $cpu_number")
        call()
    }

    fun call() {
        var call_number = mutableListOf<Int?>()
        var base_number = mutableListOf<Int?>()

        /**
         * 配列に格納
         */
        for (n in number) {
            call_number.add(n)
        }

        /**
         * 合計値の処理
         */
        var sum = presenter.numberToSum(digit, number)

        if (state == 3) {
            if (firstPlayer == 1) {
                base_number = player2_setting_number
            } else if (firstPlayer == 2) {
                base_number = player1_setting_number
            }
        } else if (state == 4) {
            if (firstPlayer == 1) {
                base_number = player1_setting_number
            } else if (firstPlayer == 2) {
                base_number = player2_setting_number
            }
        }

        /**
         * 正しいNumberか確認
         */
        val check = Check(base_number, number, state, digit, sum)
        Log.d("check", call_number.toString())
        /**
         * 正しいNumberの時の処理
         */
        if (check) {
            if (state == 1) {
                /**
                 * 先攻のNumber設定時
                 */
                if (firstPlayer == 1) {
                    turn_text.text = second_turn_text
                    for (n in call_number) {
                        player1_setting_number.add(n)
                    }
                } else if (firstPlayer == 2) {
                    turn_text.text = second_turn_text
                    for (n in call_number) {
                        player2_setting_number.add(n)
                    }
                }
                first_turn_text = resources.getString(presenter.returnFirstTurnText(firstPlayer, mode))
                second_turn_text = resources.getString(presenter.returnSecondTurnText(firstPlayer, mode))

            } else if (state == 2) {
                /**
                 * 後攻のNumber設定時
                 */
                if (firstPlayer == 1) {
                    turn_text.text = first_turn_text
                    /**
                     * 配列に格納
                     */
                    for (n in call_number) {
                        player2_setting_number.add(n)
                    }
                } else if (firstPlayer == 2) {
                    turn_text.text = first_turn_text
                    /**
                     * 配列に格納
                     */
                    for (n in call_number) {
                        player1_setting_number.add(n)
                    }
                }
            } else if (state == 3) {
                /**
                 * 先攻のNumber選択時
                 */
                turn_text.text = second_turn_text
            } else if (state == 4) {
                /**
                 * 後攻のNumber選択時
                 */
                turn_text.text = first_turn_text
            }

            if (state == 4) {
                state -= 1
            } else {
                state += 1
            }
        }

        Log.d("checklist", list.size.toString())
        if (mode == "cpu" && firstPlayer == 1 && state == 2) {
            cpuBaseNumber()
        } else if (mode == "cpu" && firstPlayer == 2 && state == 3 && list.size == 0) {
            cpuSelectNumber()
        }
    }

    fun Check(base_number_C : MutableList<Int?>, call_number_C : MutableList<Int?>, state_C : Int, digit_C : Int, sum_C : String) : Boolean {
        /**
         * 作成していたnumberリストの全ての値がnullではないことの確認
         * number.filterNotNull()でnullじゃない要素の抽出
         * number.filterNotNull().size == digitでnullじゃない要素のサイズと、
         * 選択していた桁数が等しいことを確認し次の処理へ
         */

        if (call_number_C.filterNotNull().size == digit_C) {
            var result = ""
            /**
             * 各プレイヤーの宣言numberとHit&Blowの結果表示用のリスト作成
             */
            if (state_C == 1 || state_C == 2) {

                if (mode == "local") {
                    showTurnChecngeFragment(result, state_C)
                }

            } else if (state_C == 3 || state_C == 4) {
                val hit = presenter.returnHit(base_number_C, call_number_C)
                val blow = presenter.returnBlow(base_number_C, call_number_C)

                val listAdapter = CallListAdapter(this, list)
                playerList.adapter = listAdapter

                result = resources.getString(R.string.hit_blow, hit, blow)

                /**
                 * callボタン押した時の処理
                 */
                if (flag == 0) {
                    player_result = Player()
                    player_result.player1_call = sum_C
                    player_result.player1_hit_blow = result
                    list.add(0, player_result)
                    listAdapter.notifyDataSetChanged()
                    flag = 1
                } else if (flag == 1) {
                    player_result.player2_call = sum_C
                    player_result.player2_hit_blow = result
                    listAdapter.notifyDataSetChanged()
                    flag = 0
                }

                if (mode == "cpu" && ((firstPlayer == 1 && state_C == 4) || (firstPlayer == 2 && state_C == 3))) {
//                    val hoge = presenter.cpuNumber
                    val hogehoge = mutableListOf<Int>()
                    val hoge = mutableListOf<Int?>()

                    for (n in 0 until presenter.cpuNumber.size) {
                        hogehoge.add(presenter.cpuNumber[n])
                    }

                    for (n in 0 until digit) {
                        hoge.add(0)
                    }

                    Log.d("checklisthogehoge", hogehoge.toString())
                    for (x in 0 until hogehoge.size) {

//                        for (n in 0 until digit) {
//                            if (n == 0) {
//                                hoge.add(
//                                        hogehoge[i] / (10f.pow(digit - 1)).toInt())
////                                Log.d("checklisthoge", hoge.size.toString())
//                                Log.d("checklisthogehoge", hogehoge[i].toString())
//                            } else {
//                                var sum = 0
//                                for (g in 0 until n) {
//                                    sum += hoge[g]?.times(((10f.pow(digit - 1 - g)).toInt())) ?: 0
//                                }
//                                hoge.add((hogehoge[i] - sum) / (10f.pow(digit - 1 - n)).toInt())
//                                Log.d("checklisthogehoge", hogehoge[i].toString())
//                            }
//                        }

                        for (n in 0 until digit) {
                            if (n == 0) {
//                                Log.d("check", hogehoge.size.toString())
                                hoge[n] =
                                        hogehoge[x] / (10f.pow(digit - 1)).toInt()
                            } else {
                                var sum = 0
                                for (i in 0 until n) {
                                    sum += hoge[i]?.times(((10f.pow(digit - 1 - i)).toInt())) ?: 0
                                }
                                hoge[n] = (hogehoge[x] - sum) / (10f.pow(digit - 1 - n)).toInt()
                            }
                        }

                        val removeHit = presenter.returnHit(call_number_C, hoge)
                        val removeBlow = presenter.returnBlow(call_number_C, hoge)
//                        Log.d("checkhoge", "$hoge : $removeHit : $removeBlow")
                        if (hit != removeHit || blow != removeBlow) {
                            presenter.cpuNumber.removeAll { it == hogehoge[x] }
                        }
                    }
                    Log.d("checklist", "${presenter.cpuNumber.size}")
                }

                if (hit == digit_C) {
                    val bundle = Bundle()

                    val player = presenter.getWinPlayer(state_C, firstPlayer, mode)

                    bundle.putString("win_player", player)

                    val fragment = GameResultFragment()
                    fragment.arguments = bundle
                    supportFragmentManager.beginTransaction()
                            .add(R.id.match_base, fragment)
                            .commit()
                } else {
                    showTurnChecngeFragment(result, state_C)
                }
            }

            /**
             * 選択していたNumberの初期化
             */
            radioGroup.clearCheck()
            radioGroup.removeAllViews()
            for (n in 1..digit) {
                /**
                 * radiobuttonの追加
                 */

                val radio = RadioButton(this)
                radio.background = resources.getDrawable(R.drawable.card_checked, null)
                radio.setButtonDrawable(R.drawable.trump_white)
                radio.id = n
                radioGroup.addView(radio)

                /**
                 * 配列の作成
                 */
                number[n - 1] = null
            }


            Log.d("check", "good!")

            return true

        } else {
            Log.d("check", "bad..")

            return false

        }
    }

    fun showTurnChecngeFragment(result : String, state : Int) {
        /**
         * 交代用のfragment表示
         */
        val bundle = Bundle()
        bundle.putString("result", result)

        val fragment = TurnChangeFragment(state)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
                .add(R.id.match_base, fragment)
                .commit()
    }

    fun cpuSelectNumber() {
        val selectNumber = presenter.cpuNumber.random()

        for (n in 0 until digit) {
            if (n == 0) {
                number[n] = selectNumber / (10f.pow(digit - 1)).toInt()
            } else {
                var sum = 0
                for (i in 0 until n) {
                    sum += number[i]?.times(((10f.pow(digit - 1 - i)).toInt())) ?: 0
                }
                number[n] = (selectNumber - sum) / (10f.pow(digit - 1 - n)).toInt()
            }
        }
        Log.d("checknumber", "$number : $selectNumber : ${list.size}")
        call()
    }
}
