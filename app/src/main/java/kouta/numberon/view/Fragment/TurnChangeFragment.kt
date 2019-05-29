package kouta.numberon.view.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_turn_change.view.*

import kouta.numberon.R

class TurnChangeFragment : Fragment() {

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        val view = inflater.inflate(R.layout.fragment_turn_change, container, false)

        val bundle = arguments
        val resultString = bundle?.getString("result")

        view.HitBlow.text = resultString

        view.turn_change_background.setOnClickListener {
            /**
             * 表示していたfragmentからactivityに戻す
             */
            fragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        return view
    }


}
