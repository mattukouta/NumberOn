package kouta.numberon.view.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.calc_grid_item.view.*
import kouta.numberon.R

class CalcGridAdapter() : BaseAdapter() {
    lateinit var calcs : List<Int>
    lateinit var context : Context

    constructor(context : Context, calcs : List<Int>) : this() {
        this.context = context
        this.calcs = calcs
    }

    override fun getView(position : Int, convertView : View?, parent : ViewGroup) : View? {
        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflator.inflate(R.layout.calc_grid_item, null)

        val width = parent.width
        val height = parent.height
        val dp = context.resources.displayMetrics.density
        val params = AbsListView.LayoutParams(width / 6, height)

        view.calc_grid_item.setImageResource(calcs[position])
        view.calc_grid_item.scaleX = 0.5F
        view.calc_grid_item.scaleY = 0.5F

        view.layoutParams = params

        return view
    }

    override fun getCount() : Int {
        return calcs.size
    }

    override fun getItem(position : Int) : Any {
        return 0
    }

    override fun getItemId(position : Int) : Long {
        return 0
    }
}