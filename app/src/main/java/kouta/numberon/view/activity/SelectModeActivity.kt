package kouta.numberon.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_select_mode.*
import kouta.numberon.Presenter.activity.SelectModeContract
import kouta.numberon.Presenter.activity.SelectModePresenter
import kouta.numberon.R

class SelectModeActivity : AppCompatActivity(), SelectModeContract.View {

    override lateinit var presenter : SelectModeContract.Presenter

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_mode)

        presenter = SelectModePresenter()

        /**
         * CPU対戦選択時処理
         */
        cpu_btn.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            presenter.setMode("cpu")

            intent(intent)
        }

        /**
         * ローカル対戦選択時処理
         * OrderActivityへ遷移
         */
        local_btn.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            presenter.setMode("local")

            intent(intent)
        }

        /**
         * オンライン対戦選択時処理
         */
        online_btn.setOnClickListener {
        }
    }

    /**
     * 画面遷移用の関数
     */
    override fun intent(intent : Intent) {
        startActivity(intent)
        overridePendingTransition(0, 0)
    }
}
