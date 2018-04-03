package com.eboltify.sales.ui.discount

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.eboltify.sales.R
import com.eboltify.sales.model.Discount

class DiscountAdapter(val mDiscounts: List<Discount>) : RecyclerView.Adapter<DiscountAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val context = parent?.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.discounts, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mDiscounts.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            ButterKnife.bind(itemView)
        }
    }
}