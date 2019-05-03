package kouta.numberon.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_match.*
import kouta.numberon.Model.DataUtils
import kouta.numberon.Model.Player
import kouta.numberon.Presenter.ModeTextChange
import kouta.numberon.R
import kouta.numberon.view.Adapter.ListAdapter
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

    val call = listOf("123", "124", "231")
    val hi_blow = listOf("123", "124", "231")

    var list = ArrayList<Player>()

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

//        val listAdapter = ListAdapter(this, call, hi_blow)
        val listAdapter = ListAdapter(this, list)
        playerList.adapter = listAdapter

        var count = 0

        btn_call.setOnClickListener {
            var player = Player()
            count++
            player.player1_call = "$count"
            player.player1_hit_blow = "2H&0B"
            player.player2_call = "398"
            player.player2_hit_blow = "2H&0B"
            list.add(0, player)
//            list.reverse()
//            list.
            listAdapter.notifyDataSetChanged()
        }
    }
}
