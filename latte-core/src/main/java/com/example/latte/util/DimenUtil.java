package com.example.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.latte.app.latte;

/**
 * Created by Administrator on 2017/11/30.
 */

public class DimenUtil {
    public static int getScreenWidth(){
        final Resources resources= latte.getApplication().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return dm.widthPixels;//获取屏幕宽度像素
    }
    public static int getScreenHeight(){
        final Resources resources= latte.getApplication().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
