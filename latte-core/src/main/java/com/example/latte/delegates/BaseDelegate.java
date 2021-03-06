package com.example.latte.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.latte.activities.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by Administrator on 2017/11/20.
 */

public abstract class BaseDelegate extends SwipeBackFragment{

    @SuppressWarnings("SpellCheckingInspection")//解除拼写错误
    private Unbinder mUnbinder=null;//butterknife的类型

    public abstract Object setLayout();
    public abstract void onBindView(@Nullable Bundle savedInstanceState,View rootView);


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=null;

        if (setLayout() instanceof Integer){
            rootView=inflater.inflate((Integer) setLayout(),container,false);
        }else if (setLayout() instanceof View){
            rootView=(View) setLayout();
        }
        if (rootView!=null){
            mUnbinder= ButterKnife.bind(this,rootView);//绑定视图数据view
            onBindView(savedInstanceState,rootView);
        }
        return rootView;
    }
    public final ProxyActivity getProxyActivity(){
        return (ProxyActivity) _mActivity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder!=null){
            mUnbinder.unbind();//解除绑定
        }
    }
}
