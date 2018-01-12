package com.example.latte.net.rx;

import android.content.Context;

import com.example.latte.net.HttpMethod;
import com.example.latte.net.RestCreator;
import com.example.latte.ui.loader.LatteLoader;
import com.example.latte.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/11/25.
 */
//Rxjava生命周期中，已经封装了请求

public class RxRestClient {
    private final String URL;//不想修改保证原子性，利于多线程的安全
    private static final WeakHashMap<String,Object> PARAMS=RestCreator.getParams();
    private final RequestBody BODY;//请求体\
    private final File FILE;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RxRestClient(String url, Map<String, Object> params, RequestBody body, File file, Context context, LoaderStyle loaderStyle) {
        this.URL = url;
        PARAMS.putAll(params);
        this.BODY = body;
        this.FILE=file;
        this.CONTEXT=context;
        this.LOADER_STYLE=loaderStyle;
    }
    //构造者
    public static RxRestClientBuilder builder(){
        return new RxRestClientBuilder();
    }
    private Observable<String> request(HttpMethod method){
        final RxRestService service=RestCreator.getRxRestService();
        Observable<String> observable=null;

        if (LOADER_STYLE!=null){
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }
        switch (method){
            case GET:
                observable=service.get(URL,PARAMS);
                break;
            case POST:
                observable=service.post(URL,PARAMS);
                break;
            case POST_RAW:
                observable=service.postRaw(URL,BODY);
                break;


            case PUT:
                observable=service.put(URL,PARAMS);
                break;
            case PUT_RAW:
                observable=service.putRaw(URL,BODY);
                break;
            case DELETE:
                observable=service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody=RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body=MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);//以form形式提交
                observable=service.upload(URL,body);
                break;
            default:
                break;
        }
        return observable;
    }


    public final Observable<String> get(){
        return request(HttpMethod.GET);
    }
    public final Observable<String> put(){
        if (BODY==null){
            return request(HttpMethod.PUT);
        }else {
            if (!PARAMS.isEmpty()){//参数必须是空
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.PUT_RAW);
        }
    }
    public final Observable<String> post(){
        if (BODY==null){
            return request(HttpMethod.POST);
        }else {
            if (!PARAMS.isEmpty()){//参数必须是空
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.POST_RAW);
        }

    }
    public final Observable<String> delete(){
        return request(HttpMethod.DELETE);
    }
    public final Observable<String> upload(){
        return  request(HttpMethod.UPLOAD);
    }
    public final Observable<ResponseBody> download(){
        final Observable<ResponseBody> responseBodyObservable=RestCreator.getRxRestService().download(URL,PARAMS);
        return responseBodyObservable;

    }
}
