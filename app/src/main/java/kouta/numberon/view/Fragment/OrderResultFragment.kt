package kouta.numberon.view.Fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_order_result.view.*
import kouta.numberon.Presenter.fragment.OrderResultContract
import kouta.numberon.Presenter.fragment.OrderResultPresenter

import kouta.numberon.R
import kouta.numberon.view.activity.MatchActivity

class OrderResultFragment : Fragment(), OrderResultContract.View {
    override lateinit var presenter : OrderResultContract.Presenter

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?,
                              savedInstanceState : Bundle?) : View? {
        val view = inflater.inflate(R.layout.fragment_order_result, container, false)
        val argument = arguments
        var player1 = 0
        var player2 = 0

        presenter = OrderResultPresenter()

        /**
         * カード選択値の受け取り
         */
        if (argument != null) {
            player1 = argument.getInt(presenter.getPlayer1CardKey())
            player2 = argument.getInt(presenter.getPlayer1CardKey())
        }

        val card1 = presenter.numberToCard(player1)
        val card2 = presenter.numberToCard(player2)

        view.player1_card.setImageResource(card1)
        view.player2_card.setImageResource(card2)

        if (player1 > player2) {
            view.textView.text = resources.getText(R.string.order_player1)
        } else {
            view.textView.text = resources.getText(R.string.order_player2)
            presenter.setFirstPlayer(2)
        }

        /**
         * 先行、後攻が決まりタッチでゲームスタートの処理
         */
        view.result_background.setOnClickListener {
            val intent = Intent(activity, MatchActivity::class.java)
            activity?.startActivity(intent)
            activity?.overridePendingTransition(0, 0)
        }

        return view
    }
}
