package kouta.numberon.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_select_mode.*
import kouta.numberon.R

class SelectModeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_mode)

        /**
         * CPU対戦選択時処理
         */
        cpu_btn.setOnClickListener {
//            dialogFragment.show(supportFragmentManager, "cpu")
        }

        /**
         * ローカル対戦選択時処理
         * OrderActivityへ遷移
         */
        local_btn.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("Mode", "local")
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        /**
         * オンライン対戦選択時処理
         */
        online_btn.setOnClickListener {
//            dialogFragment.show(supportFragmentManager, "online")
        }
    }
}
