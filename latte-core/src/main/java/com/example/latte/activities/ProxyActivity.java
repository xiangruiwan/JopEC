package com.example.latte.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.example.latte.R;
import com.example.latte.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Administrator on 2017/11/20.
 */

public abstract class ProxyActivity extends SupportActivity{

    public abstract LatteDelegate setRootDelegate();//返回根Delegate

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }
    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container=new ContentFrameLayout(this);//初始化根容器
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState==null){
            //初次加载
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //垃圾回收处理
        System.gc();
        System.runFinalization();
    }
}
