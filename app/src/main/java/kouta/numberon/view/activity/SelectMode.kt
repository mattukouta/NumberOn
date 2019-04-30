package kouta.numberon.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kouta.numberon.R

class SelectMode : AppCompatActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cpu_btn.setOnClickListener {
//            dialogFragment.show(supportFragmentManager, "cpu")
        }
        local_btn.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("Mode", "local")
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        online_btn.setOnClickListener {
//            dialogFragment.show(supportFragmentManager, "online")
        }
    }
}
