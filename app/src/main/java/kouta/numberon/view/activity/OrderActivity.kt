package kouta.numberon.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kouta.numberon.R
import kouta.numberon.view.Fragment.DigitDialogFragment

class OrderActivity : AppCompatActivity() {

    val dialogFragment = DigitDialogFragment()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val mode = intent.getStringExtra("Mode")

        Log.d("check", mode)
        dialogFragment.isCancelable = false
        dialogFragment.show(supportFragmentManager, "local")
    }
}
