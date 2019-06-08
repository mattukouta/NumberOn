package kouta.numberon.Presenter.fragment

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kouta.numberon.Presenter.Contract.DigitDialogContract

class DigitDialogPresenter : DigitDialogContract.Presenter {
    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * 同一TAGのダイアログが表示されているかどうかを判定する。
     * @return 同一TAGのダイアログが表示されている場合は true を返す。
     */
    override fun isSameTagDialogShowing(manager : FragmentManager, tag : String) : Boolean {
        val previousFragment = manager.findFragmentByTag(tag)
        if (previousFragment is DialogFragment) {
            val dialog = (previousFragment).dialog
            if (dialog != null && dialog.isShowing) {
                return true
            }
        }
        return false
    }
}