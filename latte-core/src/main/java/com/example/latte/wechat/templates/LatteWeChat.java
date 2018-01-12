package com.example.latte.wechat.templates;

import android.app.Activity;

import com.example.latte.app.ConfigType;
import com.example.latte.app.latte;
import com.example.latte.wechat.templates.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2017/12/11.
 */

public class LatteWeChat {
    public static final String APP_ID= latte.getConfiguration(ConfigType.WE_CHAT_APP_ID);
    public static final String APP_SECRET= latte.getConfiguration(ConfigType.WE_CHAT_APP_SECRET);

    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback=null;
    private static final class Holder{
        private static final LatteWeChat INSTANCE=new LatteWeChat();
    }
    public static LatteWeChat getInstance(){
        return Holder.INSTANCE;
    }
    private LatteWeChat(){
        final Activity activity=latte.getConfiguration(ConfigType.ACTIVITY);
        WXAPI= WXAPIFactory.createWXAPI(activity,APP_ID,true);
        WXAPI.registerApp(APP_ID);
    }
    public IWeChatSignInCallback getmSignInCallback(){
        return mSignInCallback;
    }
    public LatteWeChat onSignSuccess(IWeChatSignInCallback callback){
        this.mSignInCallback=callback;
        return this;
    }
    public final IWXAPI getWXAPI(){
        return WXAPI;
    }
    public final void signIn(){
        final SendAuth.Req req=new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }

}
