package com.example.latte.ec.icon.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.latte.app.AccountManager;
import com.example.latte.app.IUserChecker;
import com.example.latte.delegates.LatteDelegate;
import com.example.latte.ec.R;
import com.example.latte.ec.R2;
import com.example.latte.ui.launcher.ILauncherListener;
import com.example.latte.ui.launcher.OnLuancherFinishTag;
import com.example.latte.ui.launcher.ScrollLauncherTag;
import com.example.latte.util.storage.LattePreference;
import com.example.latte.util.timer.BaseTimerTask;
import com.example.latte.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/5.
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener{
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer=null;

    private int mCount=5;
    private Timer mtimer=null;
    private ILauncherListener mILauncherListener=null;
    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){
        if (mtimer!=null){
            mtimer.cancel();
            mtimer=null;
            checkIsShowScroll();
        }
    }
    private void initTimer(){
        mtimer=new Timer();
        final BaseTimerTask task=new BaseTimerTask(this);
        mtimer.schedule(task,0,1000);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            mILauncherListener=(ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }
    //判断是否显示滚动页
    private void checkIsShowScroll(){
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            start(new LauncherScrollDelegate(),SINGLETASK);//fragmention的fragment的调用方式
        }
        else {
            //检查用户是否登录了APP
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener!=null){
                        mILauncherListener.onLauncherFinish(OnLuancherFinishTag.SINGED);
                    }

                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener!=null){
                        mILauncherListener.onLauncherFinish(OnLuancherFinishTag.NOT_SINGED);
                    }

                }
            });


        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {//更新ui界面
            @Override
            public void run() {
                if (mTvTimer!=null){
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}",mCount));
                    mCount--;
                    if (mCount<0){
                        if (mtimer!=null){
                            mtimer.cancel();
                            mtimer=null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
