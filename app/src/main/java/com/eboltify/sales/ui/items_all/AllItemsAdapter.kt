package com.eboltify.sales.ui.items_all

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.eboltify.sales.R
import com.eboltify.sales.model.Item

/**
 * Created by sam_nguyen on 3/28/18.
 */
class AllItemsAdapter constructor(private val mItems: List<Item>): RecyclerView.Adapter<AllItemsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val context = parent?.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.items, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    }

    class ViewHolder constructor(itemView: View)  : RecyclerView.ViewHolder(itemView) {
        init {
            ButterKnife.bind(itemView)
        }
    }
}