package com.eboltify.sales.ui.items_management

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.eboltify.sales.R
import com.eboltify.sales.ui.base.BaseActivity
import com.eboltify.sales.ui.items_all.ItemsAllActivity

class ItemsManagementActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ItemsManagementActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    @BindView(R.id.toolbar)
    lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_management)
        changeStatusBarColor(resources.getColor(R.color.md_amber_500))
        ButterKnife.bind(this)
        initToolBar(mToolbar, getString(R.string.more_items), R.drawable.ic_move_back)
        mToolbar.setBackgroundColor(resources.getColor(R.color.md_amber_500))
        mToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    @OnClick(R.id.list_all_items)
    fun onClickListAllItems() {
        ItemsAllActivity.start(this)
    }

    @OnClick(R.id.categories)
    fun onClickCategories() {

    }

    @OnClick(R.id.modifier)
    fun onClickModifier() {

    }

    @OnClick(R.id.discount)
    fun onClickDiscount() {

    }
}
