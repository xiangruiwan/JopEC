package com.example.latte.ec.icon.main;

import android.graphics.Color;

import com.example.latte.delegates.bottom.BaseBottomDelegate;
import com.example.latte.delegates.bottom.BottomItemDelegate;
import com.example.latte.delegates.bottom.BottomTabBean;
import com.example.latte.delegates.bottom.ItemBuilder;
import com.example.latte.ec.icon.main.discover.DiscoverDelegate;
import com.example.latte.ec.icon.main.index.IndexDelegate;
import com.example.latte.ec.icon.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/12/21.
 */

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean,BottomItemDelegate> items=new LinkedHashMap<>();

        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());

        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
