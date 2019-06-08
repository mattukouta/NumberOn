package kouta.numberon.Presenter

import kouta.numberon.R

/**
 * 画面上部に表示させる用
 */

interface ModeTextChange {
    fun ModeTextChange(mode : String) : Int {
        when (mode) {
            "cpu" -> return R.string.order_title_cpu
            "local" -> return R.string.order_title_local
            "online" -> return R.string.order_title_online
        }
        return 0
    }
}