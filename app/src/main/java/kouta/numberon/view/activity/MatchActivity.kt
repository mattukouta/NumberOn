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
    var call_number = mutableListOf<Int?>()
    var base_number = mutableListOf<Int?>()

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

        radioInit()

        /**
         * 設定するnumberを選んでくださいの趣旨のテキストの取得
         */
        first_turn_text = resources.getString(presenter.returnFirstText(firstPlayer, mode))
        second_turn_text = resources.getString(presenter.returnSecondText(firstPlayer, mode))

        turn_text.text = first_turn_text

        if (mode == "cpu" && firstPlayer == 2) {

            cpuBaseNumber()

            sumCallInit()
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
     * 交代用のfragment表示
     */
    fun showTurnChecngeFragment(result : String, state : Int) {
        val bundle = Bundle()
        bundle.putString("result", result)

        val fragment = TurnChangeFragment(state)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
                .add(R.id.match_base, fragment)
                .commit()
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
                sumCallInit()
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

    /**
     * 以下メソッドわけ中
     * 現状では、presenterに移行前のものもこのActivityに書いてある
     * 修正が落ち着いたら移行する
     */

    /**
     * 与えられた引数のIntをListとして返す関数
     */
    // 変数名を変更してpresenter案件
    fun returnIntToList(hoge : Int) : MutableList<Int?> {
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
     * listからrandomに取り出し実行
     */
    // 関数内で関数の使用
    fun cpuSelectNumber() {
        val selectNumber = presenter.cpuNumber.random()

        number = returnIntToList(selectNumber)
//        sumCallInit()
    }

    /**
     * 桁に応じたlistを作成し、
     * 作成したリストからrandomに取り出し実行
     */
    // 関数内で関数の使用
    override fun cpuBaseNumber() {
        val cpu_number = presenter.createDigitList(digit)

        number = returnIntToList(cpu_number)
//        sumCallInit()
    }

    /**
     * cpu対戦時、cpuが宣言したnumberとhit&blowの結果から、
     * 条件にあったnumberをリストから削除する
     */
    fun removeList(call_number_C : MutableList<Int?>, hit : Int, blow : Int) {
        val hogehoge = mutableListOf<Int>()
        var hoge = mutableListOf<Int?>()

        for (n in 0 until presenter.cpuNumber.size) {
            hogehoge.add(presenter.cpuNumber[n])
        }

        for (n in 0 until digit) {
            hoge.add(0)
        }
        for (x in 0 until hogehoge.size) {

            hoge = returnIntToList(hogehoge[x])

            val removeHit = presenter.returnHit(call_number_C, hoge)
            val removeBlow = presenter.returnBlow(call_number_C, hoge)
            if (hit != removeHit || blow != removeBlow) {
                presenter.cpuNumber.removeAll { it == hogehoge[x] }
            }
        }
    }

    // 関数内で関数の使用
    fun sumCallInit() {
        call_number = mutableListOf()
        base_number = mutableListOf()

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
        val check = callListCheck(sum)

        stateBranch(check)
    }

    fun stateBranch(check : Boolean) {

        /**
         * 正しいNumberの時の処理
         */
        if (check) {
            if (state == 1) {

                /**
                 * 先攻のNumber設定時
                 */
                turn_text.text = second_turn_text
                if (firstPlayer == 1) {

                    player1_setting_number = call_number


                } else if (firstPlayer == 2) {

                    player2_setting_number = call_number

                }
                /**
                 * numberを選んでくださいの趣旨のテキストの取得
                 */
                first_turn_text = resources.getString(presenter.returnFirstTurnText(firstPlayer, mode))
                second_turn_text = resources.getString(presenter.returnSecondTurnText(firstPlayer, mode))

            } else if (state == 2) {
                /**
                 * 後攻のNumber設定時
                 */
                turn_text.text = first_turn_text
                if (firstPlayer == 1) {

                    player2_setting_number = call_number

                } else if (firstPlayer == 2) {

                    player1_setting_number = call_number

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
            Log.d("check", "$player1_setting_number : $player2_setting_number")
        }


        if (mode == "cpu" && firstPlayer == 1 && state == 2) {
            cpuBaseNumber()

            sumCallInit()
        } else if (mode == "cpu" && firstPlayer == 2 && state == 3 && list.size == 0) {
            cpuSelectNumber()

            sumCallInit()
        }
    }

    // 関数内で関数の使用
    fun callListCheck(sum_C : String) : Boolean {
        /**
         * 作成していたnumberリストの全ての値がnullではないことの確認
         * number.filterNotNull()でnullじゃない要素の抽出
         * number.filterNotNull().size == digitでnullじゃない要素のサイズと、
         * 選択していた桁数が等しいことを確認し次の処理へ
         */

        if (call_number.filterNotNull().size == digit) {

            callResult(sum_C)

            Log.d("check", "good!")

            return true

        } else {
            Log.d("check", "bad..")

            return false

        }
    }

    // 関数内で関数の使用
    fun callResult(sum_C : String) {
        /**
         * 各プレイヤーの宣言numberとHit&Blowの結果表示用のリスト作成
         */
        if (state == 1 || state == 2) {

            if (mode == "local") {
                showTurnChecngeFragment("", state)
            }

        } else if (state == 3 || state == 4) {
            val hit = presenter.returnHit(base_number, call_number)
            val blow = presenter.returnBlow(base_number, call_number)
            val result = resources.getString(R.string.hit_blow, hit, blow)

            showCallList(result, sum_C)

            if (mode == "cpu" && ((firstPlayer == 1 && state == 4) || (firstPlayer == 2 && state == 3))) {

                removeList(call_number, hit, blow)

            }

            if (hit == digit) {
                showGameResult(state)
            } else {
                showTurnChecngeFragment(result, state)
            }
        }

        radioInit()
    }

    /**
     *  以下はViewに関わる関数
     *  overrideは後々
     */

    /**
     * ゲーム終了と勝者の画面表示
     */
    fun showGameResult(state_C : Int) {
        val bundle = Bundle()

        val player = presenter.getWinPlayer(state_C, firstPlayer, mode)

        bundle.putString("win_player", player)

        val fragment = GameResultFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
                .add(R.id.match_base, fragment)
                .commit()
    }

    /**
     * 各プレイヤーがcallした値の表示とhit&blowを表示
     */
    fun showCallList(result : String, sum_C : String) {
        val listAdapter = CallListAdapter(this, list)
        playerList.adapter = listAdapter

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
    }

    /**
     * 選択しているradioButtonを初期化する
     */
    fun radioInit() {
        /**
         * 選択していたNumberの初期化
         */
        number = mutableListOf()
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
            number.add(null)
        }
    }
}
