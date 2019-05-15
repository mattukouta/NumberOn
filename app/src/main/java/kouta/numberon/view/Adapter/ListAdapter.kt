package kouta.numberon.view.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.playerlist.view.*
import kouta.numberon.Model.Player
import kouta.numberon.R

class ListAdapter() : BaseAdapter() {

    lateinit var player_result : ArrayList<Player>
    lateinit var context : Context
    lateinit var call : List<String>
    lateinit var hb : List<String>

    constructor(context : Context, player_result : ArrayList<Player>) : this() {
        this.context = context
        this.player_result = player_result
    }

    override fun getItem(position : Int) : Any {
        return 0
    }

    override fun getItemId(position : Int) : Long {
        return 0
    }

    override fun getCount() : Int {
        return player_result.size
    }

    override fun getView(position : Int, convertView : View?, parent : ViewGroup?) : View {
        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflator.inflate(R.layout.playerlist, null)

        /**
         * 選択numberの結果表示
         */
        view.player1_call.text = player_result.get(position).player1_call
        view.player1_hit_blow.text = player_result.get(position).player1_hit_blow
        view.player2_call.text = player_result.get(position).player2_call
        view.player2_hit_blow.text = player_result.get(position).player2_hit_blow

        return view
    }
}