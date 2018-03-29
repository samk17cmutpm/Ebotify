package com.eboltify.sales.ui.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.eboltify.sales.R;

/**
 * Created by sam_nguyen on 26/08/2017.
 */

public class TextField extends android.support.v7.widget.AppCompatTextView {

    public TextField(final Context context, final AttributeSet attrs) {
        super(context, attrs, R.attr.textFieldStyle);
    }

}

