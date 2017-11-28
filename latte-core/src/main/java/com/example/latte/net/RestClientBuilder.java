package com.example.latte.net;

import com.example.latte.net.callback.IError;
import com.example.latte.net.callback.IFailure;
import com.example.latte.net.callback.IRequest;
import com.example.latte.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/11/26.
 */

public class RestClientBuilder {
    private  String mUrl;//不想修改保证原子性，利于多线程的安全
    private  static final Map<String,Object> PARAMS=RestCreator.getParams();
    private  IRequest mIRequest;
    private  ISuccess mISuccess;
    private  IFailure mIFailure;
    private  IError mIError;
    private  ResponseBody mBody;//请求体\
    RestClientBuilder(){

    }
    public final RestClientBuilder url(String url){

        this.mUrl=url;
        return this;
    }
    public final RestClientBuilder params(WeakHashMap<String,Object> params){

        PARAMS.putAll(params);
        return this;
    }
    public final RestClientBuilder params(String key,Object value){


        PARAMS.put(key,value);
        return this;
    }
    //传入原始数据
    public final RestClientBuilder raw(String raw){

        this.mBody=ResponseBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);

        return this;
    }
    //传入请求的回调
    public final RestClientBuilder onRequest(IRequest iRequest){

        this.mIRequest=iRequest;

        return this;
    }
    //传入成功的回调
    public final RestClientBuilder success(ISuccess iSuccess){

        this.mISuccess=iSuccess;

        return this;
    }
    //传入失败的回调
    public final RestClientBuilder failure(IFailure iFailure){

        this.mIFailure=iFailure;

        return this;
    }
    //传入错误的回调
    public final RestClientBuilder error(IError iError){

        this.mIError=iError;

        return this;
    }

    public final RestClient build(){
        return new RestClient(mUrl,PARAMS,mIRequest,mISuccess,mIFailure,mIError,mBody);
    }
}
