package com.eboltify.sales.ui.categories_create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import com.eboltify.sales.R
import com.eboltify.sales.model.ColorPicker
import com.eboltify.sales.ui.base.BaseActivity
import com.eboltify.sales.ui.items_create.ColorPickerAdapter
import com.eboltify.sales.ui.lib.ItemClickSupport
import com.eboltify.sales.ui.lib.SpacesItemDecoration

class CategoriesCreateActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, CategoriesCreateActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    @BindView(R.id.toolbar)
    lateinit var mToolbar: Toolbar

    @BindView(R.id.recylcer_color_picker)
    lateinit var mRecylcerColorPicker: RecyclerView

    private var mColorPickerAdapter: ColorPickerAdapter? = null

    private var mColorPickers = ArrayList<ColorPicker>()

    private var mPickedPosition = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_create)
        changeStatusBarColor(ContextCompat.getColor(applicationContext, R.color.md_amber_500))
        ButterKnife.bind(this)
        setSupportActionBar(mToolbar)
        initToolBar(mToolbar, getString(R.string.categories_create), R.drawable.ic_move_back)
        mToolbar.setNavigationOnClickListener { onBackPressed() }
        mToolbar.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.md_amber_500))

        mColorPickers.add(ColorPicker(R.color.md_red_a700, true))
        mColorPickers.add(ColorPicker(R.color.md_orange_a700, false))
        mColorPickers.add(ColorPicker(R.color.md_purple_a700, false))
        mColorPickers.add(ColorPicker(R.color.md_indigo_a700, false))
        mColorPickers.add(ColorPicker(R.color.md_cyan_a700, false))
        mColorPickers.add(ColorPicker(R.color.md_teal_a700, false))
        mColorPickers.add(ColorPicker(R.color.md_green_a700, false))
        mColorPickers.add(ColorPicker(R.color.md_orange_a700, false))

        mColorPickerAdapter = ColorPickerAdapter(mColorPickers, applicationContext)
        mRecylcerColorPicker.adapter = mColorPickerAdapter
        mRecylcerColorPicker.layoutManager = GridLayoutManager(this, 4)
        mRecylcerColorPicker.setHasFixedSize(true)
        mRecylcerColorPicker.addItemDecoration(SpacesItemDecoration(5))

        ItemClickSupport.addTo(mRecylcerColorPicker).setOnItemClickListener { _, position, _ ->
            if (position != mPickedPosition) {
                val colorPickerUnPicked = mColorPickers[mPickedPosition]
                colorPickerUnPicked.picked = !colorPickerUnPicked.picked
                mColorPickerAdapter?.notifyItemChanged(mPickedPosition)
            }
            val colorPicker = mColorPickers[position]
            colorPicker.picked = !colorPicker.picked
            mColorPickerAdapter?.notifyItemChanged(position)
            if (colorPicker.picked) {
                mPickedPosition = position
            }
        }

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
