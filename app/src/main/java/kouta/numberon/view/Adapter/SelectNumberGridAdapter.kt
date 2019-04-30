package kouta.numberon.view.Adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.card_grid_item.view.*
import kouta.numberon.R

class SelectNumberGridAdapter() : BaseAdapter() {

    var digit = 0
    lateinit var context : Context

    constructor(context : Context, digit : Int) : this() {
        this.context = context
        this.digit = digit
    }

    override fun getView(position : Int, convertView : View?, parent : ViewGroup) : View? {
        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflator.inflate(R.layout.card_grid_item, null)

        val width = parent.width
        val height = parent.height
        val dp = context.resources.displayMetrics.density
        val params = AbsListView.LayoutParams(width / digit, height)

        view.layoutParams = params

        view.card_grid_item.setOnClickListener {
            view.card_grid_item.setImageResource(R.drawable.trump_1)
        }

        return view
    }

    override fun getCount() : Int {
        return digit
    }

    override fun getItem(position : Int) : Any {
        return 0
    }

    override fun getItemId(position : Int) : Long {
        return 0
    }
}