package kouta.numberon.view.Fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.fragment_game_result.view.*
import kouta.numberon.Presenter.fragment.GameResultContract
import kouta.numberon.Presenter.fragment.GameResultPresenter

import kouta.numberon.R
import kouta.numberon.view.activity.SelectModeActivity


class GameResultFragment : Fragment(), GameResultContract.View, View.OnClickListener {

    override lateinit var presenter : GameResultContract.Presenter
    lateinit var background : ConstraintLayout

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        val view = inflater.inflate(R.layout.fragment_game_result, container, false)
        val bundle = arguments
        val player = bundle?.getString("win_player")

        background = view.result_background

        presenter = GameResultPresenter()

        /**
         * 勝利プレイヤーの表示
         */
        view.result.text = resources.getString(R.string.match_result, player)

        background.setOnClickListener(this)

        return view
    }

    /**
     * 画面タップ時処理
     */
    override fun onClick(v : View?) {
        when (v) {
            background -> {
                intent()
            }
        }
    }

    /**
     * 画面遷移処理
     */
    override fun intent() {
        val intent = Intent(activity, SelectModeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }


}
