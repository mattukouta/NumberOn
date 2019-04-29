package kouta.numberon.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.activity_splash.*
import kouta.numberon.R
import kouta.numberon.view.Fragment.DigitDialogFragment
import org.w3c.dom.Text

class OrderActivity : AppCompatActivity() {

    val dialogFragment = DigitDialogFragment()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val mode = intent.getStringExtra("Mode")

        Log.d("check", mode)
        when (mode) {
            "local" -> select_title.setText(R.string.order_title_local)
        }

        dialogFragment.isCancelable = false
        dialogFragment.show(supportFragmentManager, "local")
    }
}
