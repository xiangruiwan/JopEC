package com.example.latte.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
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

public interface RestService {

    //get请求
    @GET
    Call<String> get(@Url String url, @QueryMap Map<String,Object> params);

    //post请求
    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String,Object> params);


    //put请求
    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url,@FieldMap Map<String,Object> params);
    //delete请求
    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String,Object> params);

    //download请求
    @Streaming//边下载边写入防止内存溢出
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String,Object> params);//特性就是一次性将文件下载然后存入内存中

    //upload请求
    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part File);
}
