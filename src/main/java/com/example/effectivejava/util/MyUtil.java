package com.example.effectivejava.util;

/**
 * 《effective java》-4：通过私有化构造器强化不可实例化的能力
 * 工具类，通过私有构造函数强化不可实例化的能力
 * 副作用就是不能被继承
 * 如果类不需要被继承，则用final修饰
 * @author Don
 * @date 2021/12/23.
 */
public final class MyUtil {
    private MyUtil(){throw new AssertionError("不可被构造");}

    public static String getId(){
        return "123";
    }
}
