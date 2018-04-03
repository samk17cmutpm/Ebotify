package com.eboltify.sales.ui.modifiers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.eboltify.sales.R
import com.eboltify.sales.model.Modifier
import com.eboltify.sales.ui.base.BaseActivity
import com.eboltify.sales.ui.lib.SpacesItemDecoration
import com.eboltify.sales.ui.modifiers_create.ModifiersCreateActivity
import com.miguelcatalan.materialsearchview.MaterialSearchView
import java.util.ArrayList

class ModifiersActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ModifiersActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    @BindView(R.id.toolbar)
    lateinit var mToolbar: Toolbar

    @BindView(R.id.search_view)
    lateinit var mMaterialSearchView: MaterialSearchView

    @BindView(R.id.swipe_refresh_layout)
    lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    @BindView(R.id.recycler_view)
    lateinit var mRecyclerView: RecyclerView

    private var mModifiers = ArrayList<Modifier>()

    private var mModifiersAdapter: ModifiersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modifiers)

        changeStatusBarColor(ContextCompat.getColor(applicationContext, R.color.md_amber_500))
        ButterKnife.bind(this)

        setSupportActionBar(mToolbar)

        initToolBar(mToolbar, getString(R.string.modifiers), R.drawable.ic_move_back)

        mToolbar.setNavigationOnClickListener { onBackPressed() }
        mToolbar.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.md_amber_500))

        mMaterialSearchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

        })

        mMaterialSearchView.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewClosed() {
            }

            override fun onSearchViewShown() {
            }
        })

        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)

        for (index in 0..99) {
            mModifiers.add(Modifier())
        }

        mModifiersAdapter = ModifiersAdapter(mModifiers)
        mRecyclerView.adapter = mModifiersAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.addItemDecoration(SpacesItemDecoration(1))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val item = menu.findItem(R.id.search)
        mMaterialSearchView.setMenuItem(item)
        return true
    }

    @OnClick(R.id.create)
    fun onClickItemCreate() {
        ModifiersCreateActivity.start(this)
    }
}
