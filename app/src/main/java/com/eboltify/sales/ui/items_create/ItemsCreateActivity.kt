package com.eboltify.sales.ui.items_create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.eboltify.sales.R
import com.eboltify.sales.model.ColorPicker
import com.eboltify.sales.ui.base.BaseActivity
import com.eboltify.sales.ui.lib.ItemClickSupport
import com.eboltify.sales.ui.lib.SpacesItemDecoration
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner

class ItemsCreateActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ItemsCreateActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    @BindView(R.id.toolbar)
    lateinit var mToolbar: Toolbar

    @BindView(R.id.items_create_categories)
    lateinit var mItemsCreateCategories: MaterialBetterSpinner

    val CATEGORIES = arrayOf("Create New Category", "Fish", "Coffee", "Scream", "Hotpot")

    @BindView(R.id.create_item_representation_recylcer_picker)
    lateinit var mCreateItemRepresentationRecylcerPicker: RecyclerView

    @BindView(R.id.create_item_representation_upload_image)
    lateinit var mCreateItemRepresentationUploadImage: View

    var mColorPickerAdapter: ColorPickerAdapter? = null

    var mColorPickers = ArrayList<ColorPicker>()

    var mPickedPosition = 0

    @BindView(R.id.items_create_representation_by)
    lateinit var mItemsCreateRepresentationBy: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_create)
        changeStatusBarColor(resources.getColor(R.color.md_amber_500))
        ButterKnife.bind(this)
        setSupportActionBar(mToolbar)
        initToolBar(mToolbar, getString(R.string.items_create), R.drawable.ic_move_back)
        mToolbar.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                onBackPressed()
            }
        })
        mToolbar.setBackgroundColor(resources.getColor(R.color.md_amber_500))
        mItemsCreateCategories.setAdapter(ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, CATEGORIES))

        mColorPickers.add(ColorPicker(R.color.md_red_a700, true))
        mColorPickers.add(ColorPicker(R.color.md_orange_a700, false))
        mColorPickers.add(ColorPicker(R.color.md_purple_a700, false))
        mColorPickers.add(ColorPicker(R.color.md_indigo_a700, false))
        mColorPickers.add(ColorPicker(R.color.md_cyan_a700, false))
        mColorPickers.add(ColorPicker(R.color.md_teal_a700, false))
        mColorPickers.add(ColorPicker(R.color.md_green_a700, false))

        mColorPickerAdapter = ColorPickerAdapter(mColorPickers, applicationContext)
        mCreateItemRepresentationRecylcerPicker.adapter = mColorPickerAdapter
        mCreateItemRepresentationRecylcerPicker.layoutManager = GridLayoutManager(this, 4)
        mCreateItemRepresentationRecylcerPicker.setHasFixedSize(true)
        mCreateItemRepresentationRecylcerPicker.addItemDecoration(SpacesItemDecoration(5))

        ItemClickSupport.addTo(mCreateItemRepresentationRecylcerPicker).setOnItemClickListener { recyclerView, position, v ->
            if (position != mPickedPosition) {
                var colorPickerUnPicked = mColorPickers[mPickedPosition]
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

        mItemsCreateRepresentationBy.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.items_create_representation_by_color -> {
                        mCreateItemRepresentationUploadImage.visibility = View.GONE
                        mCreateItemRepresentationRecylcerPicker.visibility = View.VISIBLE
                    }
                    R.id.items_create_representation_by_image -> {
                        mCreateItemRepresentationRecylcerPicker.visibility = View.GONE
                        mCreateItemRepresentationUploadImage.visibility = View.VISIBLE
                    }
                    else -> {
                    }
                }
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_items_create, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
