package kouta.numberon.view.Fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kouta.numberon.Presenter.fragment.DigitDialogContract
import kouta.numberon.Presenter.fragment.DigitDialogPresenter

import kouta.numberon.R


class DigitDialogFragment : DialogFragment(), DigitDialogContract.View, View.OnClickListener {
    override lateinit var presenter : DigitDialogContract.Presenter
    var cancel : Button? = null
    var ok : Button? = null
    var radio : RadioGroup? = null

    override fun onCreateDialog(savedInstanceState : Bundle?) : AlertDialog {
        super.onCreateDialog(savedInstanceState)

        val alert = activity?.let { AlertDialog.Builder(it) }
        val view = activity?.layoutInflater?.inflate(R.layout.fragment_digit_dialog, null)
        cancel = view?.findViewById(R.id.cancel)
        ok = view?.findViewById(R.id.ok)
        radio = view?.findViewById(R.id.radioGroup)

        presenter = DigitDialogPresenter()

        cancel?.setOnClickListener(this)
        ok?.setOnClickListener(this)

        /**
         * dialogをセット
         */
        alert?.setView(view)

        return alert?.create() as AlertDialog
    }

    override fun onClick(v : View?) {
        when (v) {
            cancel -> {
                /**
                 * selectModeに戻る
                 */
                activity?.finish()
                activity?.overridePendingTransition(0, 0)
            }
            ok -> {
                var digit = 0

                /**
                 * 選択しているradiobuttonの桁を取得
                 */
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
                    presenter.setDigit(digit)

                    dismiss()
                }
            }
        }
    }

    override fun show(manager : FragmentManager, tag : String?) {
        showNow(manager, tag)
    }

    override fun showNow(manager : FragmentManager, tag : String?) {
        if (DigitDialogPresenter().isSameTagDialogShowing(manager, tag.toString())) return
        super.showNow(manager, tag)
    }
}
