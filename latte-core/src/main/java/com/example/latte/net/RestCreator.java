package com.example.latte.net;

import com.example.latte.app.ConfigType;
import com.example.latte.app.latte;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/11/25.
 */

public class RestCreator {
    //惰性加载,单例模式Holder
    private static final class ParamsHolder{
        public static final WeakHashMap<String,Object> PARAMS=new WeakHashMap<>();
    }
    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }
    public static RestService getRRestService(){
        return RestServiceHolder.REST_SERVICE;

    }
    //单例模式
    private static final class RetrofitHolder{
        private static final String BASE_URL= (String) latte.getConfigurations().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT=new Retrofit.Builder()//简化版建造者模式,把对类的表现与构造分离开来
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }
    private static final class OKHttpHolder{
        private static final int TIME_OUT=60;
        private static final OkHttpClient OK_HTTP_CLIENT=new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)//相应时间以s为单位
                .build();


    }
    private static  final class RestServiceHolder{
        private static final RestService    REST_SERVICE=RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);//这里采用的是Java的动态代理模式
    }
}
