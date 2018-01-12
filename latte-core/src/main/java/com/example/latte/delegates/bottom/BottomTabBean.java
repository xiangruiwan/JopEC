package com.example.latte.delegates.bottom;

/**
 * Created by Administrator on 2017/12/12.
 */

public final   class BottomTabBean {
    private final CharSequence TCON;
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.TCON = icon;
        this.TITLE = title;
    }

    public CharSequence getTcon() {
        return TCON;
    }

    public CharSequence getTile() {
        return TITLE;
    }
}
