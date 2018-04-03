package com.eboltify.sales.ui.modifiers_create

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.eboltify.sales.R
import com.eboltify.sales.model.ModifierOption
import java.util.ArrayList
import android.provider.ContactsContract.CommonDataKinds.StructuredName.SUFFIX
import android.provider.ContactsContract.CommonDataKinds.StructuredName.SUFFIX





class ModifiersOptionsAdapter : RecyclerView.Adapter<ModifiersOptionsAdapter.ViewHolder>() {

    private var mModifierOptions = ArrayList<ModifierOption>()

    init {
        mModifierOptions.add(ModifierOption())
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val context = parent?.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.modifiers_options, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mModifierOptions.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.mName?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (holder.adapterPosition == (mModifierOptions.size - 1)) {
                    mModifierOptions.add(ModifierOption())
                    notifyItemInserted(mModifierOptions.size - 1)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        holder?.mDelete?.setOnClickListener(View.OnClickListener {
            if (mModifierOptions.size != 1) {
                mModifierOptions.removeAt(holder.adapterPosition)
                notifyItemRemoved(holder.adapterPosition)
            }
        })

        var addedSuffix = false
        val SUFFIX = "$"

        holder?.mMoney?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // if the only text is the suffix
                if (s.toString().equals(SUFFIX)) {
                    holder.mMoney.setText("") // clear the text
                    return
                }

                // If there is text append on SUFFIX as long as it is not there
                // move cursor back before the suffix
                if (s!!.length > 0 && !s.toString().contains(SUFFIX) && !s.toString().equals(SUFFIX)) {
                    val text = s.toString().plus(SUFFIX)
                    holder.mMoney.setText(text)
                    holder.mMoney.setSelection(text.length - SUFFIX.length)
                    addedSuffix = true // flip the addedSuffix flag to true
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if(s?.length== 0){
                    addedSuffix = false; // reset the addedSuffix flag
                }
            }

        })

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.name)
        lateinit var mName: EditText

        @BindView(R.id.money)
        lateinit var mMoney: EditText

        @BindView(R.id.delete)
        lateinit var mDelete: ImageView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}