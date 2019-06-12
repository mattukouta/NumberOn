package kouta.numberon.Presenter.activity

import android.content.Intent
import kouta.numberon.Model.Mode
import kouta.numberon.Presenter.BasePresenter
import kouta.numberon.Presenter.BaseView

interface SelectModeContract {
    interface View : BaseView<Presenter> {
        fun intent(intent : Intent)
    }

    interface Presenter : BasePresenter, Mode
}