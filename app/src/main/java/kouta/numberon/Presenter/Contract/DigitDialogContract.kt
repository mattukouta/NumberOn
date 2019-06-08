package kouta.numberon.Presenter.Contract

import androidx.fragment.app.FragmentManager
import kouta.numberon.Digit
import kouta.numberon.Presenter.BasePresenter
import kouta.numberon.Presenter.BaseView

interface DigitDialogContract {
    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter, Digit {
        fun isSameTagDialogShowing(manager : FragmentManager, tag : String) : Boolean
    }
}