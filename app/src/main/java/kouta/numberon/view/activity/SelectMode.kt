package kouta.numberon.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kouta.numberon.R
import kouta.numberon.view.Fragment.DigitDialogFragment

class SelectMode : AppCompatActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        local_btn.setOnClickListener {
//            val dialogFragment = DigitDialogFragment()
//            dialogFragment.show(supportFragmentManager, "alert")
//        }
    }
}
