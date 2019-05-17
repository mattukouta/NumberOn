package kouta.numberon.view.activity

import android.graphics.Point
import android.graphics.drawable.StateListDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_match.*
import kouta.numberon.Model.DataUtils
import kouta.numberon.Model.Player
import kouta.numberon.Presenter.ModeTextChange
import kouta.numberon.Presenter.NumberToCard
import kouta.numberon.view.Adapter.ListAdapter
import kouta.numberon.R
import kouta.numberon.view.Fragment.TurnChangeFragment


class MatchActivity : AppCompatActivity(), View.OnClickListener {
    var digit = 0
    lateinit var mode : String
    var player = 0
    var state = 0
    var turn = 1
    val calc_btn = listOf(R.drawable.trump_0, R.drawable.trump_1, R.drawable.trump_2,
            R.drawable.trump_3, R.drawable.trump_4, R.drawable.trump_eir, R.drawable.trump_5,
            R.drawable.trump_6, R.drawable.trump_7, R.drawable.trump_8, R.drawable.trump_9,
            R.drawable.trump_call)

    val call = listOf("123", "124", "231")
    val hi_blow = listOf("123", "124", "231")

    var list = ArrayList<Player>()
    var flag = 0
    lateinit var player_result : Player

    var number = mutableListOf<Int?>()

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

        /**
         * 各プレイヤーの宣言numberとHit&Blowの結果表示用のリスト作成
         */
        val listAdapter = ListAdapter(this, list)
        playerList.adapter = listAdapter

        var count = 0

        /**
         * callボタン押した時の処理
         */
//        btn_call.setOnClickListener {
//            val radio = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
//            radio.setButtonDrawable(R.drawable.trump_1)
//            if (flag == 0) {
//                count ++
//                player_result = Player()
//                player_result.player1_call = "$count"
//                player_result.player1_hit_blow = "2H&0B"
//                list.add(0, player_result)
//                listAdapter.notifyDataSetChanged()
//                flag = 1
//
//            } else if (flag == 1) {
//                player_result.player2_call = "398"
//                player_result.player2_hit_blow = "2H&0B"
//                listAdapter.notifyDataSetChanged()
//                flag = 0
//
//            }
//
//        }

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
        var flag = 0
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
                /**
                 * 作成していたnumberリストの全ての値がnullではないことの確認
                 * number.filterNotNull()でnullじゃない要素の抽出
                 * number.filterNotNull().size == digitでnullじゃない要素のサイズと、
                 * 選択していた桁数が等しいことを確認し次の処理へ
                 */
                if (number.filterNotNull().size == digit) {
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

        for (n in number) {
            if (n == select_number) {
                flag = 1
            }
        }

        /**
         * 画面下の選択した数字を選択していたradioButtonに画面に差し込み
         */

        if (radioGroup.checkedRadioButtonId != - 1 && v != btn_call && flag != 1) {
            val radio = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            radio.setButtonDrawable(select_card)
            number[radioGroup.checkedRadioButtonId - 1] = select_number
        }
    }

}
