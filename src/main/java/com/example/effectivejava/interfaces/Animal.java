package com.example.effectivejava.interfaces;

/**
 * 《effective java》-21：为后代设计接口
 * 1、接口的设计一定要考虑所有实现它的后代行为
 * 2、接口的设计要通用，新增的默认方法不要破坏已有后代的行为
 * 3、对于不通用的接口默认方法，后代要去覆盖实现
 * @author Don
 * @date 2022/2/21.
 */
public interface Animal {

     /**
       * 飞翔，设计不好，没有考虑有些动物是不能飞翔的
       * @author: Don
       * @date: 2022/2/21 15:26
       **/
    default void fly(){
        System.out.println("i can fly");
    }

     /**
       * 动物名称
       * @author: Don
       * @date: 2022/2/21 15:28
       **/
    String name();

     /**
       * 这里就是举个例子，狗是不会飞翔的，说明这个接口设计的不太好，那我们必须去覆盖默认行为
       * @author: Don
       * @date: 2022/2/21 15:27
       **/
    public class Dog implements Animal{

        @Override
         public void fly(){
             System.out.println("i can't fly");
         }

         @Override
         public String name() {
             return "dog";
         }
     }

    public static void main(String[] args) {
        Animal animal = new Dog();
        animal.fly();
    }
}
