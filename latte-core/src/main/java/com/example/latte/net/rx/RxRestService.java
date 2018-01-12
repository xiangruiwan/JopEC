package com.example.latte.net.rx;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/11/25.
 */

public interface RxRestService {

    //get请求
    @GET
    Observable<String> get(@Url String url, @QueryMap Map<String, Object> params);

    //post请求
    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap Map<String, Object> params);
    @POST
    Observable<String> postRaw(@Url String url, @Body RequestBody body);
    //put请求
    @FormUrlEncoded
    @PUT
    Observable<String> put(@Url String url, @FieldMap Map<String, Object> params);
    @PUT
    Observable<String> putRaw(@Url String url, @Body RequestBody body);
    //delete请求
    @DELETE
    Observable<String> delete(@Url String url, @QueryMap Map<String, Object> params);

    //download请求
    @Streaming//边下载边写入防止内存溢出
    @GET
    Observable<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);//特性就是一次性将文件下载然后存入内存中

    //upload请求上传
    @Multipart
    @POST
    Observable<String> upload(@Url String url, @Part MultipartBody.Part File);
}
