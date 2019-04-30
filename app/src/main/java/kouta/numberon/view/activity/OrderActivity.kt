package kouta.numberon.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_order.*
import kouta.numberon.R
import kouta.numberon.view.Fragment.DigitDialogFragment
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import kouta.numberon.Model.DataUtils
import kouta.numberon.view.Fragment.OrderResultFragment
import java.util.Collections


class OrderActivity : AppCompatActivity(), View.OnClickListener {

    val dialogFragment = DigitDialogFragment()
    val card_number = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    var view : View? = null
    var player1 = 10
    var player2 = 10
    var select = 10
    var card_base = R.drawable.trump_re
    var select_card = R.drawable.trump_re_green
    var digit_data = 0

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val mode = intent.getStringExtra("Mode")

        Log.d("check", mode)
        when (mode) {
            "local" -> select_title.setText(R.string.order_title_local)
        }

        dialogFragment.isCancelable = false
        dialogFragment.setTargetFragment(null, DataUtils().DIGIT_REQUEST_CODE)
        dialogFragment.show(supportFragmentManager, "local")

        zero.setOnClickListener(this)
        one.setOnClickListener(this)
        two.setOnClickListener(this)
        three.setOnClickListener(this)
        four.setOnClickListener(this)
        five.setOnClickListener(this)
        six.setOnClickListener(this)
        seven.setOnClickListener(this)
        eight.setOnClickListener(this)
        nine.setOnClickListener(this)
        select_btn.setOnClickListener(this)
    }

    override fun onClick(v : View?) {
        when (v) {
            zero -> select = 0
            one -> select = 1
            two -> select = 2
            three -> select = 3
            four -> select = 4
            five -> select = 5
            six -> select = 6
            seven -> select = 7
            eight -> select = 8
            nine -> select = 9
            select_btn -> {
                if (select != 10) {
                    //player1の決定時
                    if (player1 == 10 && player2 == 10) {
                        select_text.text = resources.getText(R.string.select_player2)
                        player1 = select
                        view?.isEnabled = false
                        view = null
                        select_card = R.drawable.trump_re_red
                        //player2の決定時
                    } else if (player1 != 10 && player2 == 10 && player1 != select) {
                        Collections.shuffle(card_number)
                        player2 = select
//                        Toast.makeText(this, "Player1:" + card_number[player1].toString() + ", Player2:" + card_number[player2] + "桁数は:$digit_data", Toast.LENGTH_LONG).show()
                        val bundle = Bundle()
                        bundle.putInt(DataUtils().PLAYER1_CARD, card_number[player1])
                        bundle.putInt(DataUtils().PLAYER2_CARD, card_number[player2])

                        val fragment = OrderResultFragment()
                        fragment.arguments = bundle

                        supportFragmentManager.beginTransaction()
                                .add(R.id.order_base, fragment)
                                .commit()
                    }
                }
            }
        }

        if (v != null && v != select_btn) {

            if (view != null && v != view) {
                view?.background = ResourcesCompat.getDrawable(resources, card_base, null)
            }
            v.background = ResourcesCompat.getDrawable(resources, select_card, null)
            view = v
        }
    }

    override fun onActivityResult(requestCode : Int, resultCode : Int, data : Intent?) {
        if (requestCode == DataUtils().DIGIT_REQUEST_CODE && resultCode == DataUtils().DIGIT_RESULT_CODE) {
            if (data != null) {
                digit_data = data.getIntExtra("digit", 0)
            }
        }
    }

    /**
     * バックキーの動作の変更
     */
    override fun onBackPressed() {
        finish()
        overridePendingTransition(0, 0)
    }
}
