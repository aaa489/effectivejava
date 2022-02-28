package com.example.effectivejava;

import java.util.*;

/**
 * 《effective java》-42：lambda表达式优于匿名类
 * lambda使得代码更简洁，编译器使用称为类型推断的过程从上下文中推导出这些类型
 * 一行代码对于 lambda 说是理想的，三行代码是合理的最大值
 * 除非必须创建非函数式接口类型的实例，否则不要使用匿名类作为函数对象。
 * ---------------------
 * 《effective java》-43：方法引用优于 lambda 表达式
 * 方法引用通常为 lambda 提供一个更简洁的选择。 如果方法引用看起来更简短更清晰，请使用它们；否则，还是坚持 lambda
 * @author Don
 * @date 2022/2/25.
 */
public class LambdaClass {

    private List<String> words = Arrays.asList("A", "B","C");

    public void sort(){

        //匿名类方式
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });

        //lambda方式
        Collections.sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
    }

    public void methodRef(){
        Map<String, Integer> map = new HashMap<>();

        //使用lambda表达式
        map.merge("key", 1, (count, incr) -> count + incr);

        //使用函数引用，显得更加清晰
        map.merge("key", 2, Integer::sum);
    }
}
