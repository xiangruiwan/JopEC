package com.example.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/11/14.
 */
//该类是进行配置文件的存储
public class Configurator {
    //使用WeakHashMap的原因是在我们不用的键值对会及时回收，避免内存溢出
    private static  final HashMap<String,Object> LATTE_CONFIGS=new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS=new ArrayList<>();
    private Configurator(){
        //ConfigType.CONFIG_READY.name()枚举类的方法以字符串的形式输出出来
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);//配置未完成
    }
    public static Configurator getInstance(){
        return Holder.INSTANCE;
        //很完美的配合静态内部类的单例模式
    }
    private static class Holder{
        //使用静态内部类来完成对Configurator单例的初始化
        private static final Configurator INSTANCE=new Configurator();
    }
    final HashMap<String,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }
    public final void configure(){
        initIcons();
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
    private void checkConfiguration(){
        final boolean isReady=(boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready call configure");
        }

    }
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T)LATTE_CONFIGS.get(key.name());

    }
}
