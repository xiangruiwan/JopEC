package com.example.latte.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/11/14.
 */
//不让别人继承latte这个类
public final class latte {
    //初始化app
    public static Configurator init(Context context){
        //调用WeakHashMap 里的put方法传入初始化配置参数
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();//返回的是Configurator里的INSTANCE这个对象

    }
    public static HashMap<String,Object> getConfigurations(){
        //拿到Configurator里面的LATTE_CONFIGS这个对象
        return Configurator.getInstance().getLatteConfigs();
    }
    public static Context getApplication(){
        return  (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
