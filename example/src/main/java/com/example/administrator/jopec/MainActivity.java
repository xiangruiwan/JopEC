package com.example.administrator.jopec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.example.latte.activities.ProxyActivity;
import com.example.latte.app.latte;
import com.example.latte.delegates.LatteDelegate;
import com.example.latte.ec.icon.launcher.LauncherDelegate;
import com.example.latte.ec.icon.main.EcBottomDelegate;
import com.example.latte.ec.icon.sign.ISignListener;
import com.example.latte.ec.icon.sign.SignInDelegate;
import com.example.latte.ui.launcher.ILauncherListener;
import com.example.latte.ui.launcher.OnLuancherFinishTag;

import qiu.niorgai.StatusBarCompat;

public class MainActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(getApplication(),"登录成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(getApplication(),"注册成功",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLauncherFinish(OnLuancherFinishTag tag) {
        switch (tag){
            case SINGED:
                Toast.makeText(getApplication(),"启动结束，用户登陆了",Toast.LENGTH_SHORT);
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SINGED:
                Toast.makeText(getApplication(),"启动结束，用户没登陆",Toast.LENGTH_SHORT);
                startWithPop(new SignInDelegate());

                break;
            default:
                break;
        }
    }
}
