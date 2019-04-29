package kouta.numberon.view.Fragment

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

import kouta.numberon.R


class DigitDialogFragment : DialogFragment() {


    override fun onCreateDialog(savedInstanceState : Bundle?) : Dialog {
        super.onCreateDialog(savedInstanceState)

        val alert = activity?.let { AlertDialog.Builder(it) }
        val view = activity?.layoutInflater?.inflate(R.layout.fragment_digit_dialog, null)

        alert?.setView(view)


        val cancel = view?.findViewById<Button>(R.id.cancel)
        val ok = view?.findViewById<Button>(R.id.ok)
        val radio = view?.findViewById<RadioGroup>(R.id.radioGroup)


        cancel?.setOnClickListener { activity?.finish() }
        ok?.setOnClickListener {
            when (radio?.checkedRadioButtonId) {
                R.id.two -> dismiss()
                R.id.three -> dismiss()
                R.id.four -> dismiss()
                R.id.five -> dismiss()
            }
        }

        return alert?.create()!!
    }

    override fun show(manager : FragmentManager, tag : String?) {
        showNow(manager, tag)
    }

    override fun showNow(manager : FragmentManager, tag : String?) {
        if (isSameTagDialogShowing(manager, tag.toString())) return
        super.showNow(manager, tag)
    }

    /**
     * 同一TAGのダイアログが表示されているかどうかを判定する。
     * @return 同一TAGのダイアログが表示されている場合は true を返す。
     */
    private fun isSameTagDialogShowing(manager : FragmentManager, tag : String) : Boolean {
        val previousFragment = manager.findFragmentByTag(tag)
        if (previousFragment is DialogFragment) {
            val dialog = (previousFragment as DialogFragment).getDialog()
            if (dialog != null && dialog.isShowing()) {
                return true
            }
        }
        return false
    }
}
