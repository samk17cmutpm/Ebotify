package com.eboltify.sales.ui.base

/**
 * Created by sam_nguyen on 3/28/18.
 */
interface BaseView {
    /**
     * Initialize Dependency Injections
     */
    fun initDI()

    /**
     * Initialize UI
     */
    fun initUI()

    /**
     * Initialize Data
     */
    fun initData()
}