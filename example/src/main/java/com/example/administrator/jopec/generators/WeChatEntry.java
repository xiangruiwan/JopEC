package com.example.administrator.jopec.generators;

import com.example.annotations.EntryGenerator;
import com.example.latte.wechat.templates.WXEntryTemplate;

/**
 * Created by Administrator on 2017/12/10.
 */

@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.example.administrator.jopec",
        entryTemplete = WXEntryTemplate.class//要继承的类
)
public interface WeChatEntry {
}
