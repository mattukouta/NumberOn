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

        presenter = MatchPresenter()
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


        //        if (firstPlayer == 1) {
        //            turn_text.text = resources.getText(R.string.player1_select)
        //        } else if (firstPlayer == 2) {
        //            turn_text.text = resources.getText(R.string.player2_select)
        //        }
        //        turn_text.text = resources.getText(

        first_turn_text = resources.getString(presenter.returnFirstText(firstPlayer, mode))
        second_turn_text = resources.getString(presenter.returnSecondText(firstPlayer, mode))

        turn_text.text = first_turn_text

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
                        base_number = player1_setting_number
                    } else if (firstPlayer == 2) {
                        base_number = player2_setting_number
                    }
                } else if (state == 4) {
                    if (firstPlayer == 1) {
                        base_number = player2_setting_number
                    } else if (firstPlayer == 2) {
                        base_number = player1_setting_number
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
                /**
                 * 交代用のfragment表示
                 */
                val bundle = Bundle()
                bundle.putString("result", result)

                val fragment = TurnChangeFragment()
                fragment.arguments = bundle
                supportFragmentManager.beginTransaction()
                        .add(R.id.match_base, fragment)
                        .commit()

            } else if (state_C == 3 || state_C == 4) {
                val hit = MatchPresenter().returnHit(base_number_C, call_number_C)
                val blow = MatchPresenter().returnBlow(base_number_C, call_number_C)

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

                if (hit == digit_C) {
                    val bundle = Bundle()
                    if (state_C == 3) {
                        if (firstPlayer == 1) {
                            bundle.putString("win_player", "Player1")
                        } else if (firstPlayer == 2) {
                            bundle.putString("win_player", "Player2")
                        }
                    } else if (state_C == 4) {
                        if (firstPlayer == 1) {
                            bundle.putString("win_player", "Player2")
                        } else if (firstPlayer == 2) {
                            bundle.putString("win_player", "Player1")
                        }
                    }
                    val fragment = GameResultFragment()
                    fragment.arguments = bundle
                    supportFragmentManager.beginTransaction()
                            .add(R.id.match_base, fragment)
                            .commit()
                } else {
                    /**
                     * 交代用のfragment表示
                     */
                    val bundle = Bundle()
                    bundle.putString("result", result)

                    val fragment = TurnChangeFragment()
                    fragment.arguments = bundle
                    supportFragmentManager.beginTransaction()
                            .add(R.id.match_base, fragment)
                            .commit()
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

}
