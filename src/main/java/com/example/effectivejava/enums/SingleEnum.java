package com.example.effectivejava.enums;

import java.util.UUID;

/**
 * 《effective java》-3：使用私有构造器或者枚举类型来强化Singleton属性
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

     /**
       * 私有构造函数及
       * @author: Don
       * @date: 2022/2/18 10:59
       **/
    public static class Single {
        private static final Single INSTANCE = new Single();
        private Single() { }
        public static Single getInstance(){
            return INSTANCE;
        }
    }
}