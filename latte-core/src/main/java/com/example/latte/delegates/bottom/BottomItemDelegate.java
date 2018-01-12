package com.example.latte.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.latte.R;
import com.example.latte.delegates.LatteDelegate;

/**
 * Created by Administrator on 2017/12/12.
 */

public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener{

    private long mExitTime=0;
    private static final int EXIT_TIME=2000;

    @Override
    public void onResume() {
        super.onResume();
        final View rootView=getView();
        if (rootView!=null){
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i==KeyEvent.KEYCODE_BACK&&keyEvent.getAction()==KeyEvent.ACTION_DOWN){
            if ((System.currentTimeMillis()-mExitTime)>EXIT_TIME){
                Toast.makeText(getContext(),"双击退出"+getString(R.string.app_name),Toast.LENGTH_SHORT).show();
                mExitTime=System.currentTimeMillis();
            }else {
                _mActivity.finish();
                if (mExitTime!=0){
                    mExitTime=0;
                }
            }
            return true;//说明系统执行了

        }
        return false;
    }
}
