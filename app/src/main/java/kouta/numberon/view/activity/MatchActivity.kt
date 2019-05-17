package kouta.numberon.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_match.*
import kouta.numberon.Model.DataUtils
import kouta.numberon.Model.Player
import kouta.numberon.Presenter.ModeTextChange
import kouta.numberon.Presenter.NumberToCard
import kouta.numberon.R
import kouta.numberon.view.Adapter.ListAdapter
import kouta.numberon.view.Fragment.TurnChangeFragment
import java.lang.Math.pow


class MatchActivity : AppCompatActivity(), View.OnClickListener {
    var digit = 0
    lateinit var mode : String
    var player = 0
    var state = 1
    val calc_btn = listOf(R.drawable.trump_0, R.drawable.trump_1, R.drawable.trump_2,
            R.drawable.trump_3, R.drawable.trump_4, R.drawable.trump_eir, R.drawable.trump_5,
            R.drawable.trump_6, R.drawable.trump_7, R.drawable.trump_8, R.drawable.trump_9,
            R.drawable.trump_call)

    var number = mutableListOf<Int?>()
    var flag = 0

    var list = ArrayList<Player>()
    lateinit var player_result : Player

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        digit = intent.getIntExtra(DataUtils().DIGIT, 0)
        mode = intent.getStringExtra(DataUtils().MODE)
        player = intent.getIntExtra(DataUtils().PLAYER, 0)
        select_title.setText(ModeTextChange(mode))

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

        if (player == 1) {
            turn_text.text = resources.getText(R.string.player1_select)
            state = 1
        } else if (player == 2) {
            turn_text.text = resources.getText(R.string.player2_select)
            state = 2
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
     * 画面下の数字およびcallボタン選択時の処理
     */
    override fun onClick(v : View?) {
        var select_card = 0
        var select_number = 0
        var number_flag = 0
        when (v) {
            btn_0 -> {
                select_number = 0
                select_card = NumberToCard(select_number)
            }
            btn_1 -> {
                select_number = 1
                select_card = NumberToCard(select_number)
            }
            btn_2 -> {
                select_number = 2
                select_card = NumberToCard(select_number)
            }
            btn_3 -> {
                select_number = 3
                select_card = NumberToCard(select_number)
            }
            btn_4 -> {
                select_number = 4
                select_card = NumberToCard(select_number)
            }
            btn_5 -> {
                select_number = 5
                select_card = NumberToCard(select_number)
            }
            btn_6 -> {
                select_number = 6
                select_card = NumberToCard(select_number)
            }
            btn_7 -> {
                select_number = 7
                select_card = NumberToCard(select_number)
            }
            btn_8 -> {
                select_number = 8
                select_card = NumberToCard(select_number)
            }
            btn_9 -> {
                select_number = 9
                select_card = NumberToCard(select_number)
            }
            btn_call -> {
                var sum = ""
                if (state == 1) {
                    sum = NumberToSum(digit, number)
                } else if (state == 2) {
                    sum = NumberToSum(digit, number)
                }

                Log.d("checksum", sum)

                Check(number, state, digit, sum)
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

    fun NumberToSum(digit_NTS : Int, number_NTS : MutableList<Int?>) : String {
        /**
         * ここで配列numberを数字に変換する。
         */

        var index = 10.0
        var digit_number = 0
        index = pow(index, (digit_NTS - 1).toDouble())

        for (n in number_NTS) {
            if (n != null) {
                digit_number += n * index.toInt()
                index /= 10
            }
        }
//        Log.d("checksum", String.format("%0${digit_NTS}d", digit_number))

        return String.format("%0${digit_NTS}d", digit_number)
    }

    fun Check(number_C : MutableList<Int?>, state_C : Int, digit_C : Int, sum_C : String) {
        /**
         * 作成していたnumberリストの全ての値がnullではないことの確認
         * number.filterNotNull()でnullじゃない要素の抽出
         * number.filterNotNull().size == digitでnullじゃない要素のサイズと、
         * 選択していた桁数が等しいことを確認し次の処理へ
         */

        if (number_C.filterNotNull().size == digit_C) {
            /**
             * 各プレイヤーの宣言numberとHit&Blowの結果表示用のリスト作成
             */
            val listAdapter = ListAdapter(this, list)
            playerList.adapter = listAdapter


            /**
             * callボタン押した時の処理
             */
            if (flag == 0) {
                player_result = Player()
                player_result.player1_call = sum_C
                player_result.player1_hit_blow = sum_C
                list.add(0, player_result)
                listAdapter.notifyDataSetChanged()
                flag = 1
            } else if (flag == 1) {
                player_result.player2_call = sum_C
                player_result.player2_hit_blow = sum_C
                listAdapter.notifyDataSetChanged()
                flag = 0
            }

            Log.d("check", "good!")
            val fragment = TurnChangeFragment()

            supportFragmentManager.beginTransaction()
                    .add(R.id.match_base, fragment)
                    .commit()
        } else {
            Log.d("check", "bad..")
        }
    }

}
