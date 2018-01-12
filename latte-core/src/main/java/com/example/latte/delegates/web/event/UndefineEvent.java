package com.example.latte.delegates.web.event;

import com.example.latte.util.log.LatteLogger;

/**
 * Created by Administrator on 2018/1/8.
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent", params);
        return null;
    }
}
