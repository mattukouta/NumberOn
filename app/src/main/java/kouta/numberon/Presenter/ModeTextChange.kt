package kouta.numberon.Presenter

import kouta.numberon.R

fun ModeTextChange(mode : String) : Int {
    when (mode) {
        "cpu" -> return R.string.order_title_cpu
        "local" -> return R.string.order_title_local
        "online" -> return R.string.order_title_online
    }
    return 0
}