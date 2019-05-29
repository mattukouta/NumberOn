package kouta.numberon.Presenter.fragment

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kouta.numberon.Model.gameInfo

class DigitDialogPresenter {
    fun setDigit(digit : Int) {
        gameInfo.gameDigit = digit
    }

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