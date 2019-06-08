package kouta.numberon.view.Fragment

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kouta.numberon.Presenter.fragment.DigitDialogPresenter

import kouta.numberon.R


class DigitDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState : Bundle?) : AlertDialog {
        super.onCreateDialog(savedInstanceState)

        val alert = activity?.let { AlertDialog.Builder(it) }
        val view = activity?.layoutInflater?.inflate(R.layout.fragment_digit_dialog, null)
        val cancel = view?.findViewById<Button>(R.id.cancel)
        val ok = view?.findViewById<Button>(R.id.ok)
        val radio = view?.findViewById<RadioGroup>(R.id.radioGroup)

        /**
         * dialogをセット
         */
        alert?.setView(view)


        cancel?.setOnClickListener {
            activity?.finish()
            activity?.overridePendingTransition(0, 0)
        }
        ok?.setOnClickListener {

            var digit = 0

            when (radio?.checkedRadioButtonId) {
                R.id.two -> digit = 2
                R.id.three -> digit = 3
                R.id.four -> digit = 4
                R.id.five -> digit = 5
            }

            if (digit != 0) {
                /**
                 * 選択した桁数をActivityに反映
                 */
                presemter.setDigit(digit)

                dismiss()
            }
        }

        return alert?.create() as AlertDialog
    }

    override fun show(manager : FragmentManager, tag : String?) {
        showNow(manager, tag)
    }

    override fun showNow(manager : FragmentManager, tag : String?) {
        if (DigitDialogPresenter().isSameTagDialogShowing(manager, tag.toString())) return
        super.showNow(manager, tag)
    }
}
