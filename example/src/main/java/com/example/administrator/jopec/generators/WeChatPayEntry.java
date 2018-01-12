package com.example.administrator.jopec.generators;

import com.example.annotations.PayEntryGenerator;
import com.example.latte.wechat.templates.WXPayEntryTemplate;

/**
 * Created by Administrator on 2017/12/10.
 */
@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.example.administrator.jopec",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
