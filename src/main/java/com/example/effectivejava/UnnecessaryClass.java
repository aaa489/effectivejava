package com.example.effectivejava;

/**
 * 《effective java》-6：避免创建不必要的对象
 * 1、如果一个对象是不可变的，那么它总是能被复用
 * 2、避免自动装箱、拆箱（性能损耗）
 * 3、轻量级小对象的创建和回收代价是很低的，通过创建额外的对象来加强代码的清晰、简单或者功能，这通常是件好事
 * @author Don
 * @date 2022/2/18.
 */
public class UnnecessaryClass {

    //这条语句每次被执行时都会产生一个新的String实例，但这些对象的创建都是不必要的
    public void badWay(){
        String s = new String("bikini");
    }

    //改进的版本，只用了一个String实例（常量池中），而不是每次执行时都创建一个新的
    public void goodWay(){
        String s = "bikini";
    }

    //出现了自动装箱，相对性能比较慢
    public void boxInt(){
        Integer sum = 0;
        for (int i = 0; i <= Integer.MAX_VALUE; i++)
        {
            sum += i;
        }
    }
}
