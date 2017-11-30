package com.example.latte.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.latte.R;
import com.example.latte.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/30.
 */

public class LatteLoader {
    private static final int LOADER_SIZE_SCALE=8;//默认宽高比8
    private static final int LOADER_OFFSET_SCALE=10;//偏移量10
    private static final ArrayList<AppCompatDialog> LOADERS=new ArrayList<>();
    private static final String DEFAULT_LOADER=LoaderStyle.BallClipRotatePulseIndicator.name();
    public static void showLoading(Context context,Enum<LoaderStyle> type){
        showLoading(context,type.name());
    }
    public static void showLoading(Context context,String type){
        final AppCompatDialog dialog=new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView=LoaderCreator.create(type,context);
        dialog.setContentView(avLoadingIndicatorView);
        int devicewidth= DimenUtil.getScreenWidth();
        int deviceheight=DimenUtil.getScreenWidth();
        final Window dialogWindow=dialog.getWindow();
        if (dialogWindow!=null){
            WindowManager.LayoutParams lp=dialogWindow.getAttributes();
            lp.width=devicewidth/LOADER_SIZE_SCALE;//缩8倍
            lp.height=deviceheight/LOADER_SIZE_SCALE;
            lp.height=lp.height+deviceheight/LOADER_OFFSET_SCALE;
            lp.gravity= Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }
    public static void showLoading(Context context){
        showLoading(context,DEFAULT_LOADER);

    }
    public static void stopLoading(){
        for (AppCompatDialog dialog:LOADERS) {
            if (dialog!=null){
                if (dialog.isShowing()){
                    dialog.cancel();//关闭还会执行回调
                    //dialog.dismiss();//单纯的关闭
                }
            }

        }

    }

}
