package com.example.latte.app;

import com.example.latte.util.storage.LattePreference;

/**
 * Created by Administrator on 2017/12/8.
 */

public class AccountManager {
    private enum SignTag{
        SIGN_TAG
    }
    //保存用户登录状态，登陆后调用
    public static void setSignState(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }
    private static boolean isSignIn(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }
    public static void checkAccount(IUserChecker checker){
        if (isSignIn()){
            checker.onSignIn();
        }else {
            checker.onNotSignIn();
        }

    }
}
