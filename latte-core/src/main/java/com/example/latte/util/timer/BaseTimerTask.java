package com.example.latte.util.timer;

import java.util.TimerTask;

/**
 * Created by Administrator on 2017/12/5.
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener=null;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener!=null){
            mITimerListener.onTimer();
        }

    }
}
