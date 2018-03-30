package com.eboltify.sales.ui.main

import android.animation.ValueAnimator
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import com.eboltify.sales.R
import com.eboltify.sales.ui.base.BaseActivity
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.MenuItem
import android.view.Window
import butterknife.BindView
import butterknife.ButterKnife
import com.eboltify.sales.ui.lib.*
import com.eboltify.sales.ui.main.home.HomeFragment
import com.eboltify.sales.ui.main.inbox.InboxFragment
import com.eboltify.sales.ui.main.more.MoreFragment
import com.eboltify.sales.ui.main.reports.ReportsFragment
import com.eboltify.sales.ui.main.sales.SalesFragment


class MainActivity : BaseActivity() {

    val TAG = "Main"

    @BindView(R.id.navigation)
    lateinit var mBottomNavigationView: BottomNavigationView

    @BindView(R.id.view_pager)
    lateinit var mViewPager: ViewPager

    val mItems = ArrayList<Item>()

    lateinit var mMainPagerAdapter: MainPagerAdapter

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnBottomItemSelectedListener)
        BottomNavigationViewHelper.removeShiftMode(mBottomNavigationView);
        mMainPagerAdapter = MainPagerAdapter(supportFragmentManager)
        mViewPager.adapter = mMainPagerAdapter
        initItems()
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            var mPrimaryAnimator: ValueAnimator? = null
            var mAccentAnimator: ValueAnimator? = null

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }
            override fun onPageSelected(position: Int) {

                if (mPrimaryAnimator?.isRunning == true)
                    mPrimaryAnimator?.cancel()

                if (mAccentAnimator?.isRunning == true)
                    mAccentAnimator?.cancel()

                val item = mItems.get(position)
                val primary = CCFAnimator.rgb(mPrimaryColor, item.primaryColor)
                mPrimaryAnimator = primary.asValueAnimator(CCFAnimator.OnNewColorListener { color ->
                    mWindowCompat.setStatusBarColor(color)
                }
                )
                mPrimaryAnimator?.duration = 250L
                mPrimaryAnimator?.start()

                val accent = CCFAnimator.rgb(
                        accentColor(mAccentColor, mPrimaryColor),
                        accentColor(item.accentColor, item.primaryColor)
                )

                mAccentAnimator = accent.asValueAnimator(CCFAnimator.OnNewColorListener { color ->
                    mBottomNavigationView.setBackgroundColor(color)
                })

                mAccentAnimator?.duration = 250L
                mAccentAnimator?.start()

                mPrimaryColor = item.primaryColor
                mAccentColor = item.accentColor

                when (position) {
                    MainMenuType.HOME -> mBottomNavigationView.selectedItemId = R.id.home
                    MainMenuType.SALES -> mBottomNavigationView.selectedItemId = R.id.sales
                    MainMenuType.INBOX -> mBottomNavigationView.selectedItemId = R.id.inbox
                    MainMenuType.REPORTS -> mBottomNavigationView.selectedItemId = R.id.reports
                    MainMenuType.MORE -> mBottomNavigationView.selectedItemId = R.id.more
                }
            }

        })
    }

    fun initItems() {

        mItems.add(Item(
                ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary),
                ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary),
                "First Teal")
        )

        mItems.add(Item(
                ContextCompat.getColor(getApplicationContext(), R.color.md_red_500),
                ContextCompat.getColor(getApplicationContext(), R.color.md_red_300),
                "Second Red")
        )

        mItems.add(Item(
                ContextCompat.getColor(getApplicationContext(), R.color.md_purple_500),
                ContextCompat.getColor(getApplicationContext(), R.color.md_purple_300),
                "Third Purple")
        )
        mItems.add(Item(
                ContextCompat.getColor(getApplicationContext(), R.color.md_blue_500),
                ContextCompat.getColor(getApplicationContext(), R.color.md_blue_300),
                "Forth Blue")
        )

        mItems.add(Item(
                ContextCompat.getColor(getApplicationContext(), R.color.md_amber_500),
                ContextCompat.getColor(getApplicationContext(), R.color.md_amber_300),
                "Fith Amber")
        )

    }

    fun accentColor(accentColor: Int, primaryColor: Int): Int {
        val out: Int
        val animator = CCFAnimator.rgb(accentColor, primaryColor)
        out = animator.getColor(1F)
        return out
    }

    private val mOnBottomItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.home -> {
                    mViewPager.setCurrentItem(MainMenuType.HOME, true)
                    return true
                }
                R.id.sales -> {
                    mViewPager.setCurrentItem(MainMenuType.SALES, true)
                    return true
                }
                R.id.inbox -> {
                    mViewPager.setCurrentItem(MainMenuType.INBOX, true)
                    return true
                }
                R.id.reports -> {
                    mViewPager.setCurrentItem(MainMenuType.REPORTS, true)
                    return true
                }
                R.id.more -> {
                    mViewPager.setCurrentItem(MainMenuType.MORE, true)
                    return true
                }
            }
            return false
        }
    }

    class Item constructor(val primaryColor: Int, val accentColor: Int, val title: String)

    class MainPagerAdapter constructor(fragmenetManager: android.support.v4.app.FragmentManager?) : SmartFragmentStatePagerAdapter(fragmenetManager) {

        private val TOTAL_PAGES = 5;

        override fun getCount(): Int {
            return TOTAL_PAGES
        }

        override fun getItem(position: Int): Fragment {
            when (position) {
                MainMenuType.HOME -> return HomeFragment.newInstance()
                MainMenuType.SALES -> return SalesFragment.newInstance()
                MainMenuType.INBOX -> return InboxFragment.newInstance()
                MainMenuType.REPORTS -> return ReportsFragment.newInstance()
                MainMenuType.MORE -> return MoreFragment.newInstance()
            }
            return HomeFragment.newInstance()
        }

        override fun getPageTitle(position: Int): CharSequence {
            when (position) {
                MainMenuType.HOME -> return R.string.bottom_navigation_home.toString()
                MainMenuType.SALES -> return R.string.bottom_navigation_sales.toString()
                MainMenuType.INBOX -> return R.string.bottom_navigation_inbox.toString()
                MainMenuType.REPORTS -> return R.string.bottom_navigation_reports.toString()
                MainMenuType.MORE -> return R.string.bottom_navigation_more.toString()
            }
            return R.string.bottom_navigation_home.toString()
        }
    }

    class MainMenuType {
        companion object {
            val HOME = 0
            val SALES = 1
            val INBOX = 2
            val REPORTS = 3
            val MORE = 4
        }
    }
}
