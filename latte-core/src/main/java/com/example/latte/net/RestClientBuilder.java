package com.example.latte.net;

import android.content.Context;

import com.example.latte.net.callback.IError;
import com.example.latte.net.callback.IFailure;
import com.example.latte.net.callback.IRequest;
import com.example.latte.net.callback.ISuccess;
import com.example.latte.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/11/26.
 */

public class RestClientBuilder {
    private String mUrl = null;//不想修改保证原子性，利于多线程的安全
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;//请求体\
    private Context mcontext = null;
    private LoaderStyle mloaderstyle = null;
    private File mFile = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {

        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {

        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {


        PARAMS.put(key, value);
        return this;
    }

    //上传调用
    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    //下载的文件名
    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    //下载目录
    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    //后缀名
    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    //传入原始数据
    public final RestClientBuilder raw(String raw) {

        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);

        return this;
    }

    //传入文本和加载的风格样式
    public final RestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.mcontext = context;
        this.mloaderstyle = loaderStyle;
        return this;
    }

    //默认loader
    public final RestClientBuilder loader(Context context) {
        this.mcontext = context;
        this.mloaderstyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    //传入请求的回调
    public final RestClientBuilder onRequest(IRequest iRequest) {

        this.mIRequest = iRequest;

        return this;
    }

    //传入成功的回调
    public final RestClientBuilder success(ISuccess iSuccess) {

        this.mISuccess = iSuccess;

        return this;
    }

    //传入失败的回调
    public final RestClientBuilder failure(IFailure iFailure) {

        this.mIFailure = iFailure;

        return this;
    }

    //传入错误的回调
    public final RestClientBuilder error(IError iError) {

        this.mIError = iError;

        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mDownloadDir, mExtension, mName, mIRequest, mISuccess, mIFailure, mIError, mBody, mFile, mcontext, mloaderstyle);
    }
}
