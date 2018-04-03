package com.eboltify.sales.ui.modifiers_create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import com.eboltify.sales.R
import com.eboltify.sales.model.ModifierOption
import com.eboltify.sales.ui.base.BaseActivity
import com.eboltify.sales.ui.lib.SpacesItemDecoration
import java.util.ArrayList

class ModifiersCreateActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ModifiersCreateActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    @BindView(R.id.toolbar)
    lateinit var mToolbar: Toolbar

    @BindView(R.id.recycler_view)
    lateinit var mRecyclerView: RecyclerView

    private var mModifiersOptions = ArrayList<ModifierOption>()

    private var mModifiersOptionsAdapter: ModifiersOptionsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modifiers_create)
        changeStatusBarColor(ContextCompat.getColor(applicationContext, R.color.md_amber_500))
        ButterKnife.bind(this)
        setSupportActionBar(mToolbar)
        initToolBar(mToolbar, getString(R.string.modifiers_create), R.drawable.ic_move_back)
        mToolbar.setNavigationOnClickListener { onBackPressed() }
        mToolbar.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.md_amber_500))
        mModifiersOptions.add(ModifierOption())
        mModifiersOptionsAdapter = ModifiersOptionsAdapter()
        mRecyclerView.adapter = mModifiersOptionsAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.addItemDecoration(SpacesItemDecoration(1))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_create, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
