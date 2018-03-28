package com.eboltify.sales.ui.base

import android.support.v7.widget.Toolbar

/**
 * Created by sam_nguyen on 3/28/18.
 */
interface BaseViewActions {

    /**
     * Init Toolbar
     * @param toolbar
     * @param title
     * @param icon
     */
    fun initToolBar(toolbar: Toolbar, title: String, icon: Int)

    /**
     * When Token Is Expired Or Server Is Broken
     * @param code
     */
    fun handleStatusCodeError(code: Int)

    /**
     * When Time Out Or Internet Is Broken
     * @param message
     */
    fun handleInternetException(message: String)

}