package com.eboltify.sales.ui.items_create

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.eboltify.sales.R
import com.eboltify.sales.model.ColorPicker

/**
 * Created by sam_nguyen on 3/28/18.
 */
class ColorPickerAdapter(val mColorPickers: List<ColorPicker>, val context: Context)  : RecyclerView.Adapter<ColorPickerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val context = parent?.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.color_picker_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mColorPickers.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val colorPicker = mColorPickers[position]
        holder?.mRoot?.setBackgroundColor(context.resources.getColor(colorPicker.color))
        if (colorPicker.picked) {
            holder?.mPicked?.visibility = View.VISIBLE
        } else {
            holder?.mPicked?.visibility = View.VISIBLE
        }
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            ButterKnife.bind(this, itemView)
        }

        @BindView(R.id.root)
        lateinit var mRoot: View

        @BindView(R.id.picked)
        lateinit var mPicked: View
    }
}