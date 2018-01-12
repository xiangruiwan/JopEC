package com.example.administrator.jopec.generators;

import com.example.annotations.AppRegisterGenerator;
import com.example.latte.wechat.templates.AppRegisterTemplate;

/**
 * Created by Administrator on 2017/12/10.
 */
@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.example.administrator.jopec",
        registerTemplete = AppRegisterTemplate.class
)
public interface AppRegister {
}
