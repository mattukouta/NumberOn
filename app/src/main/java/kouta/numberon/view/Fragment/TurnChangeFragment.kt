package kouta.numberon.view.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_turn_change.view.*
import kouta.numberon.Presenter.fragment.TurnChangeContract
import kouta.numberon.Presenter.fragment.TurnChangePresenter

import kouta.numberon.R
import kouta.numberon.view.activity.MatchActivity

class TurnChangeFragment(private val state : Int) : Fragment(), TurnChangeContract.View {
    override lateinit var presenter : TurnChangeContract.Presenter

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        val view = inflater.inflate(R.layout.fragment_turn_change, container, false)
        val bundle = arguments
        val resultString = bundle?.getString("result")

        presenter = TurnChangePresenter()

        val mode = presenter.getMode()
        val firstPlayer = presenter.getFirstPlayer()
        /**
         * call結果の表示
         */
        view.HitBlow.text = resultString

        /**
         * 表示していたfragmentからactivityに戻す
         */
        view.turn_change_background.setOnClickListener {
            fragmentManager?.beginTransaction()?.remove(this)?.commit()

            if (mode == "cpu" && ((firstPlayer == 1 && state == 3) || (firstPlayer == 2 && state == 4))) {
                val activity : MatchActivity = activity as MatchActivity
                activity.cpuBaseNumber()
            }
        }

        return view
    }


}
