package kouta.numberon.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_match.*
import kouta.numberon.Model.DataUtils
import kouta.numberon.Presenter.ModeTextChange
import kouta.numberon.R
import kouta.numberon.view.Adapter.CalcGridAdapter
import kouta.numberon.view.Adapter.SelectNumberGridAdapter


class MatchActivity : AppCompatActivity() {

    var digit = 0
    lateinit var mode : String
    var player = 0
    var state = 0
    var turn = 1
    val calc_btn = listOf(R.drawable.trump_0, R.drawable.trump_1, R.drawable.trump_2,
            R.drawable.trump_3, R.drawable.trump_4, R.drawable.trump_eir, R.drawable.trump_5,
            R.drawable.trump_6, R.drawable.trump_7, R.drawable.trump_8, R.drawable.trump_9,
            R.drawable.trump_call)

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        digit = intent.getIntExtra(DataUtils().DIGIT, 0)
        mode = intent.getStringExtra(DataUtils().MODE)
        player = intent.getIntExtra(DataUtils().PLAYER, 0)

//        turn_text.setText()
        select_title.setText(ModeTextChange(mode))


        val select_adapter = SelectNumberGridAdapter(this, digit)
        select_gridView.numColumns = digit
        select_gridView.adapter = select_adapter

//        val calc_adapter = CalcGridAdapter(this, calc_btn)
//        calc_gridView.adapter = calc_adapter
    }
}
