package kouta.numberon.Presenter.Contract

import android.content.Intent
import kouta.numberon.Presenter.BasePresenter
import kouta.numberon.Presenter.BaseView

interface SelectModeContract {
    interface View : BaseView<Presenter> {
        fun intent(intent : Intent)
    }

    interface Presenter : BasePresenter{
        fun setMode(mode : String)
    }
}