package com.example.latte.net.download;

import android.os.AsyncTask;

import com.example.latte.net.RestCreator;
import com.example.latte.net.callback.IError;
import com.example.latte.net.callback.IFailure;
import com.example.latte.net.callback.IRequest;
import com.example.latte.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/12/2.
 */

public class DownloadHandler {
    private final String URL;//不想修改保证原子性，利于多线程的安全
    private static final WeakHashMap<String,Object> PARAMS= RestCreator.getParams();
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;//下载到哪个目录
    private final String EXTENSION;//下载文件后缀名
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    public DownloadHandler(String URL, IRequest REQUEST, String DOWNLOAD_DIR, String EXTENSION, String NAME, ISuccess SUCCESS, IFailure FAILURE, IError ERROR) {
        this.URL = URL;
        this.REQUEST = REQUEST;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.EXTENSION = EXTENSION;
        this.NAME = NAME;
        this.SUCCESS = SUCCESS;
        this.FAILURE = FAILURE;
        this.ERROR = ERROR;
    }

    public final void handleDownload(){
        if (REQUEST!=null){
            REQUEST.onRequestStart();
        }
        RestCreator.getRRestService().download(URL,PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    final ResponseBody responseBody=response.body();//拿到请求体
                    final SaveFileTask task=new SaveFileTask(REQUEST,SUCCESS);
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,responseBody,NAME);

                    //这里一定要注意判断，否则文件下载不全
                    if (task.isCancelled()){
                        if (REQUEST!=null){
                            REQUEST.onRequestEnd();
                        }
                    }
                }else {
                    if (ERROR!=null){
                        ERROR.onError(response.code(),response.message());
                    }
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (FAILURE!=null){
                    FAILURE.onFailure();
                }

            }
        });

    }
}
