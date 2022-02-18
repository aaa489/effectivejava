package com.example.effectivejava.construct;

/**
 * 《effective java》-1：考虑用静态方法而不是构造器
 * 优势：
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
