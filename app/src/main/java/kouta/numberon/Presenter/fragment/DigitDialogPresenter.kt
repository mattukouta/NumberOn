package kouta.numberon.Presenter.fragment

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class DigitDialogPresenter {
    /**
     * 同一TAGのダイアログが表示されているかどうかを判定する。
     * @return 同一TAGのダイアログが表示されている場合は true を返す。
     */
    fun isSameTagDialogShowing(manager : FragmentManager, tag : String) : Boolean {
        val previousFragment = manager.findFragmentByTag(tag)
        if (previousFragment is DialogFragment) {
            val dialog = (previousFragment).getDialog()
            if (dialog != null && dialog.isShowing()) {
                return true
            }
        }
        return false
    }
}