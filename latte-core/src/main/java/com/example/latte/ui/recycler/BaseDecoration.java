package com.example.latte.ui.recycler;

import android.support.annotation.ColorInt;
import com.choices.divider.DividerItemDecoration;
/**
 * Created by Administrator on 2018/1/5.
 */

public class BaseDecoration extends DividerItemDecoration {

    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static BaseDecoration create(@ColorInt int color, int size) {
        return new BaseDecoration(color, size);
    }
}
