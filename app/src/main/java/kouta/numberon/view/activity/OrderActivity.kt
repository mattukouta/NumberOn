package kouta.numberon.view.activity

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_order.*
import kouta.numberon.R
import kouta.numberon.view.Fragment.DigitDialogFragment
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat


class OrderActivity : AppCompatActivity(), View.OnClickListener {

    val dialogFragment = DigitDialogFragment()
    var view : View? = null
    var player1 = 10
    var player2 = 10
    var select = 10
    var card_base = R.drawable.trump_re
    var select_card = R.drawable.trump_re_green

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val mode = intent.getStringExtra("Mode")

        Log.d("check", mode)
        when (mode) {
            "local" -> select_title.setText(R.string.order_title_local)
        }

        dialogFragment.isCancelable = false
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
                    if (player1 == 10 && player2 == 10) {
                        player1 = select
                        view?.isEnabled = false
                        view = null
                        select_card = R.drawable.trump_re_red
                    } else if (player1 != 10 && player2 == 10) {
                        player2 = select
                        Toast.makeText(this, "Player1:$player1, Player2:$player2", Toast.LENGTH_LONG).show()
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

    /**
     * バックキーの動作の変更
     */
    override fun onBackPressed() {
        finish()
        overridePendingTransition(0, 0)
    }
}
