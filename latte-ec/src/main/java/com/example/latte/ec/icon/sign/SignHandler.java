package com.example.latte.ec.icon.sign;

import com.example.latte.app.AccountManager;

/**
 * Created by Administrator on 2017/12/8.
 */

public class SignHandler {
    public static void onSignIn(String response,ISignListener signListener) {
//        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
//        if (profileJson==null){
//            Toast.makeText(latte.getApplication(),"没取到json数据",Toast.LENGTH_LONG).show();
//        }
//        final long userId = profileJson.getLong("userId");
//        final String name = profileJson.getString("name");
//        final String avatar = profileJson.getString("avatar");
//        final String gender = profileJson.getString("gender");
//        final String address = profileJson.getString("address");
//
//        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
//        DatabaseManager.getInstance().getDao().insert(profile);

        //登录成功了
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();

    }
    public static void onSignUp(String response,ISignListener signListener) {
//        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
//        if (profileJson==null){
//            Toast.makeText(latte.getApplication(),"没取到json数据",Toast.LENGTH_LONG).show();
//        }
//        final long userId = profileJson.getLong("userId");
//        final String name = profileJson.getString("name");
//        final String avatar = profileJson.getString("avatar");
//        final String gender = profileJson.getString("gender");
//        final String address = profileJson.getString("address");
//
//        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
//        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();

    }
}
