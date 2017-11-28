package com.example.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by Administrator on 2017/11/16.
 */

public enum  EcIcons implements Icon{
    icon_scan('\ue609'),
    icon_ali_pay('\ue65e')
    ;
    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return this.character;
    }

}
