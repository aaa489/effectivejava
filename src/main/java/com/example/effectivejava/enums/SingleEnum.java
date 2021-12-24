package com.example.effectivejava.enums;

import java.util.UUID;

/**
 * 枚举方式来使用单例模式
 * 1、防止被显示调用构造函数创建
 * 2、防止序列化等生成新对象
 * @author Don
 * @date 2021/12/23.
 */
public enum SingleEnum {
    INSTANCE;

    public String createId(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
