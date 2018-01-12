package com.example.latte.net.callback;

import android.os.Handler;

import com.example.latte.ui.loader.LatteLoader;
import com.example.latte.ui.loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/11/28.
 */
//Ctrl
public class RequestCallbacks implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler hander=new Handler();//使用static避免内存泄漏

    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error,LoaderStyle style) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE=style;
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE!=null){
            FAILURE.onFailure();
        }
        if (REQUEST!=null){
            REQUEST.onRequestEnd();
        }
        stopLoading();

    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){//请求成功
            if (call.isExecuted()){//call执行了
                if (SUCCESS!=null){
                    SUCCESS.onSuccess(response.body());
                }

            }
        }
        else {
            if (ERROR!=null){
                ERROR.onError(response.code(),response.message());
            }
        }
        stopLoading();

    }
    public  void stopLoading(){
        if (LOADER_STYLE!=null){
            hander.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },1000);

        }
    }
}
