package com.example.latte.net.rx;

import android.content.Context;

import com.example.latte.net.RestCreator;
import com.example.latte.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/11/26.
 */

public class RxRestClientBuilder {
    private  String mUrl=null;//不想修改保证原子性，利于多线程的安全
    private  static final Map<String,Object> PARAMS=RestCreator.getParams();

    private RequestBody mBody=null;//请求体\
    private Context mcontext=null;
    private LoaderStyle mloaderstyle=null;
    private File mFile=null;
    RxRestClientBuilder(){

    }
    public final RxRestClientBuilder url(String url){

        this.mUrl=url;
        return this;
    }
    public final RxRestClientBuilder params(WeakHashMap<String,Object> params){

        PARAMS.putAll(params);
        return this;
    }
    public final RxRestClientBuilder params(String key, Object value){


        PARAMS.put(key,value);
        return this;
    }
    //上传调用
    public final RxRestClientBuilder file(File file){
        this.mFile=file;
        return this;
    }
    public final RxRestClientBuilder file(String file){
        this.mFile=new File(file);
        return this;
    }
    //传入原始数据
    public final RxRestClientBuilder raw(String raw){

        this.mBody=RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);

        return this;
    }
    //传入文本和加载的风格样式
    public final RxRestClientBuilder loader(Context context, LoaderStyle loaderStyle){
        this.mcontext=context;
        this.mloaderstyle=loaderStyle;
        return this;
    }
    //默认loader
    public final RxRestClientBuilder loader(Context context){
        this.mcontext=context;
        this.mloaderstyle= LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RxRestClient build(){
        return new RxRestClient(mUrl,PARAMS,mBody,mFile,mcontext,mloaderstyle);
    }
}
