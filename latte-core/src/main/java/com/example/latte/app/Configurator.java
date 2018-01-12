package com.example.latte.app;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.example.latte.delegates.web.event.Event;
import com.example.latte.delegates.web.event.EventManager;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by Administrator on 2017/11/14.
 */
//该类是进行配置文件的存储
public class Configurator {
    //使用WeakHashMap的原因是在我们不用的键值对会及时回收，避免内存溢出
    private static  final HashMap<Object,Object> LATTE_CONFIGS=new HashMap<>();
    private static final Handler HANDLER = new Handler();
    private static final ArrayList<IconFontDescriptor> ICONS=new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS=new ArrayList<>();
    private Configurator(){
        //ConfigType.CONFIG_READY.name()枚举类的方法以字符串的形式输出出来
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);//配置未完成
        LATTE_CONFIGS.put(ConfigType.HANDLER, HANDLER);
    }
    public static Configurator getInstance(){
        return Holder.INSTANCE;
        //很完美的配合静态内部类的单例模式
    }
    private static class Holder{
        //使用静态内部类来完成对Configurator单例的初始化
        private static final Configurator INSTANCE=new Configurator();
    }
    final HashMap<Object,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }
    public final void configure(){
        initIcons();
        Logger.addLogAdapter(new AndroidLogAdapter());
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);//配置完成
    }
    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }
    private void initIcons(){
        if (ICONS.size()>0){
            final Iconify.IconifyInitializer initializer=Iconify.with(ICONS.get(0));
            for (int i=1;i<ICONS.size();i++){
                initializer.with(ICONS.get(i));
            }
        }
    }
    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);

        return this;

    }
    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
        return this;
    }
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
        return this;
    }
    private void checkConfiguration(){
        final boolean isReady=(boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready call configure");
        }

    }
    public final Configurator withWeChatAppId(String appId){

        LATTE_CONFIGS.put(ConfigType.WE_CHAT_APP_ID,appId);
        return this;

    }
    public final Configurator withWeChatAppSecret(String appSecret){

        LATTE_CONFIGS.put(ConfigType.WE_CHAT_APP_SECRET,appSecret);
        return this;

    }
    public final Configurator withActivity(Activity activity){

        LATTE_CONFIGS.put(ConfigType.ACTIVITY,activity);
        return this;

    }
    public Configurator withJavascriptInterface(@NonNull String name) {
        LATTE_CONFIGS.put(ConfigType.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    public Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }
}
