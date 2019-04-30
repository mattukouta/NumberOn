package kouta.numberon.view.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_order_result.*
import kotlinx.android.synthetic.main.fragment_order_result.view.*
import kouta.numberon.Model.DataUtils

import kouta.numberon.R

class OrderResultFragment : Fragment() {

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?,
                              savedInstanceState : Bundle?) : View? {
        val view = inflater.inflate(R.layout.fragment_order_result, container, false)
        val argument = arguments
        var player1 = 0
        var player2 = 0

        if (argument != null) {
            player1 = argument.getInt(DataUtils().PLAYER1_CARD)
            player2 = argument.getInt(DataUtils().PLAYER2_CARD)
        }

        val card1 = NumberToCard(player1)
        val card2 = NumberToCard(player2)

        view.player1_card.setImageResource(card1)
        view.player2_card.setImageResource(card2)

        if (player1 > player2) {
            view.textView.text = resources.getText(R.string.order_player1)
        } else {
            view.textView.text = resources.getText(R.string.order_player2)
        }

        view.result_background.setOnClickListener {
            activity?.finish()
            activity?.overridePendingTransition(0, 0)
        }

        return view
    }

    private fun NumberToCard(number : Int) : Int {
        when (number) {
            0 -> return R.drawable.trump_0
            1 -> return R.drawable.trump_1
            2 -> return R.drawable.trump_2
            3 -> return R.drawable.trump_3
            4 -> return R.drawable.trump_4
            5 -> return R.drawable.trump_5
            6 -> return R.drawable.trump_6
            7 -> return R.drawable.trump_7
            8 -> return R.drawable.trump_8
            9 -> return R.drawable.trump_9
        }
        return 0
    }
}
