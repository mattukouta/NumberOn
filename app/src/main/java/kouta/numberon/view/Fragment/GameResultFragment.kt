package kouta.numberon.view.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_game_result.view.*

import kouta.numberon.R


class GameResultFragment : Fragment() {

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        val view = inflater.inflate(R.layout.fragment_game_result, container, false)
        val bundle = arguments
        val player = bundle?.getString("win_player")

        view.result.text = player

        return view
    }


}
