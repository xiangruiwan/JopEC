package com.example.administrator.jopec;

import android.app.Application;

import com.example.latte.app.latte;
import com.example.latte.ec.icon.database.DatabaseManager;
import com.example.latte.ec.icon.icon.FontEcModule;
import com.example.latte.net.interceptors.DebugInterceptor;
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
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .withJavascriptInterface("latte")
                .withInterceptor(new DebugInterceptor("index",R.raw.index_data))
                .withInterceptor(new DebugInterceptor("sort",R.raw.sort_list_data))
                .withInterceptor(new DebugInterceptor("content_data1",R.raw.sort_content_data_1))
                .withInterceptor(new DebugInterceptor("content_data2",R.raw.sort_content_data_2))
                .configure();
        DatabaseManager.getInstance().init(this);
    }
}
