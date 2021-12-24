package com.example.effectivejava.construct;

/**
 * 静态函数代替构造函数
 * 1、可以指定名称
 * 2、可以复用对象
 * 3、可以返回子类
 * @author Don
 * @date 2021/12/23.
 */
public class Animal {

    private final static Animal animal = new Animal();

    public static Animal getAnimal(){
        return new Animal();
    }

    public static Animal getSingleAnimal(){
        return animal;
    }
}
