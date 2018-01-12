package com.example.latte.app;

/**
 * Created by Administrator on 2017/11/14.
 */
//枚举类在整个应用程序中是一个单例并且只能被初始化一次
//进行多线程操作时可以用这个进行安全惰性的初始化-线程安全的懒汉模式
public enum ConfigType {
    //配置网络请求域名的
    API_HOST,
    //全局的上下文
    APPLICATION_CONTEXT,
    //控制初始化和配置完成
    CONFIG_READY,
    //存储自己的初始化项目
    ICON,
    INTERCEPTOR,
//微信的初始化
    WE_CHAT_APP_ID,
    WE_CHAT_APP_SECRET,
    ACTIVITY,
    HANDLER,
    JAVASCRIPT_INTERFACE
}
