package com.example.latte.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/12/12.
 */

public final class ItemBuilder {
    private final LinkedHashMap<BottomTabBean,BottomItemDelegate> ITEMS=new LinkedHashMap<>();
    static ItemBuilder builder(){

        return new ItemBuilder();

    }
    public final ItemBuilder addItem(BottomTabBean bean,BottomItemDelegate delegate){

        ITEMS.put(bean,delegate);

        return this;
    }
    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean,BottomItemDelegate> items){

        ITEMS.putAll(items);

        return this;

    }
    public final LinkedHashMap<BottomTabBean,BottomItemDelegate> build(){

        return ITEMS;

    }
}
