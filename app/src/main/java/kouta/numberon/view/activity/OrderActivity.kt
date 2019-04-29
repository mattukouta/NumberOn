package kouta.numberon.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_order.*
import kouta.numberon.R
import kouta.numberon.view.Fragment.DigitDialogFragment
import android.view.View


class OrderActivity : AppCompatActivity(), View.OnClickListener {

    val dialogFragment = DigitDialogFragment()

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
    }

    override fun onClick(v : View?) {
        when (v) {
            zero -> Log.d("check", "hoge")
            one -> Log.d("check", "hoge")
            two -> Log.d("check", "hoge")
            three -> Log.d("check", "hoge")
            four -> Log.d("check", "hoge")
            five -> Log.d("check", "hoge")
            six -> Log.d("check", "hoge")
            seven -> Log.d("check", "hoge")
            eight -> Log.d("check", "hoge")
            nine -> Log.d("check", "hoge")
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
