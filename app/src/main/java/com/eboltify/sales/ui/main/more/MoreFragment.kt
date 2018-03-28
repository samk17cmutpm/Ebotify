package com.eboltify.sales.ui.main.more


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick

import com.eboltify.sales.R
import com.eboltify.sales.ui.items_management.ItemsManagementActivity


/**
 * A simple [Fragment] subclass.
 * Use the [MoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoreFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_more, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment MoreFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(): MoreFragment {
            val fragment = MoreFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @OnClick(R.id.more_items)
    fun onClickMoreItems() {
        ItemsManagementActivity.start(activity)
    }

    @OnClick(R.id.customer_care)
    fun onClickCustomerCare() {

    }

    @OnClick(R.id.settings)
    fun onClickSettings() {

    }

    @OnClick(R.id.sign_out)
    fun onClickSignOut() {

    }

}// Required empty public constructor
