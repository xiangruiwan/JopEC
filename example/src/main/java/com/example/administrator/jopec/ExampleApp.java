package com.example.administrator.jopec;

import android.app.Application;

import com.example.latte.app.latte;
import com.example.latte.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by Administrator on 2017/11/14.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1/")
                .configure();
    }
}
