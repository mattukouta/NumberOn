package kouta.numberon.view.Fragment

import android.app.Dialog
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kouta.numberon.Presenter.DataUtils
import kouta.numberon.Presenter.fragment.DigitDialogPresenter

import kouta.numberon.R


class DigitDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState : Bundle?) : Dialog {
        super.onCreateDialog(savedInstanceState)

        val alert = activity?.let { AlertDialog.Builder(it) }
        val view = activity?.layoutInflater?.inflate(R.layout.fragment_digit_dialog, null)
        val cancel = view?.findViewById<Button>(R.id.cancel)
        val ok = view?.findViewById<Button>(R.id.ok)
        val radio = view?.findViewById<RadioGroup>(R.id.radioGroup)
        var digit = 0

        /**
         * dialogをセット
         */
        alert?.setView(view)


        cancel?.setOnClickListener {
            activity?.finish()
            activity?.overridePendingTransition(0, 0)
        }
        ok?.setOnClickListener {

            val intent = Intent()

            when (radio?.checkedRadioButtonId) {
                R.id.two -> digit = 2
                R.id.three -> digit = 3
                R.id.four -> digit = 4
                R.id.five -> digit = 5
            }

            /**
             * 選択した桁数をActivityに反映
             */
            intent.putExtra(DataUtils().DIGIT, digit)
            val pi = activity?.createPendingResult(targetRequestCode, intent, PendingIntent.FLAG_ONE_SHOT)

            if (pi != null) {
                pi.send(DataUtils().DIGIT_RESULT_CODE)
            }
            dismiss()
        }
        //アンラップになっているので要修正事項
        return alert?.create() !!
    }

    override fun show(manager : FragmentManager, tag : String?) {
        showNow(manager, tag)
    }

    override fun showNow(manager : FragmentManager, tag : String?) {
        if (DigitDialogPresenter().isSameTagDialogShowing(manager, tag.toString())) return
        super.showNow(manager, tag)
    }
}
