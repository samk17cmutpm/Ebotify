package com.eboltify.sales.ui.modifiers

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.eboltify.sales.R
import com.eboltify.sales.model.Modifier

class ModifiersAdapter(private val mModifiers: List<Modifier>) : RecyclerView.Adapter<ModifiersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val context = parent?.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.modifiers, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mModifiers.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            ButterKnife.bind(itemView)
        }
    }

}