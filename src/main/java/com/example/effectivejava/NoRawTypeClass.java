package com.example.effectivejava;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 《effective java》-26：不要使用原始类型
 * 如果你使用原始类型，则会丧失泛型的所有安全性和表达上的优势
 * 原始类型不能在编译期发现错误，运行期可能抛出异常引起安全隐患，在编译期检查出错误是最理想的
 * 参数化类型是原始类型的子类：List<String> 是原始类型List 的子类型，但不是参数化类型 List<Object> 的子类型
 * 术语介绍：
 *         术语             | 中文含义 |         举例
 * Parameterized type      | 参数化类型 |    List<String>
 * Actual type parameter   | 实际类型参数 |     String
 * Generic type            | 泛型类型 |         List<E>
 * Formal type parameter   | 形式类型参数 |      E
 * Unbounded wildcard type | 无限制通配符类型 |  List<?>
 * Raw type                | 原始类型 |         List
 * Bounded type parameter  | 限制类型参数 | <E extends Number>
 * Recursive type bound    | 递归类型限制 | <T extends Comparable<T>>
 * Bounded wildcard type   | 限制通配符类型 | List<? extends Number>
 * Generic method          | 泛型方法 |     static <E> List<E> asList(E[] a)
 * Type token             | 类型令牌 |        String.class
 * @author Don
 * @date 2022/2/22.
 */
public class NoRawTypeClass {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
//        unsafeAdd(strings, Integer.valueOf(42));
//        String str = strings.get(0); // Has compiler-generated cast
//        System.out.println(str);

        //泛型类使用原生类型来做实例判断
        if (strings instanceof List){
            System.out.println(true);
        }

        //以下三行编译期会报错，通配符用于形参取值，不用于实参使用
//        List<?> listOfIntegers = new ArrayList<Integer>();
//        List<? extends Integer> listOfIntegers2 = new ArrayList<Integer>();
//        listOfIntegers.add(1);
    }


    //使用了原始类型，运行时抛出异常
    private static void unsafeAdd(List list, Object o) {
        list.add(o);
    }

    //使用了参数化类型，编译器会提示错误
//    private static void unsafeAdd2(List<String> list, Object o) {
//        list.add(o);
//    }

    //使用了原始类型，是不安全的
    private static int numElementsInCommon(Set s1, Set s2) {
        int result = 0;
        for (Object o1 : s1){
            if (s2.contains(o1)){
                result++;
            }
        }
        return result;
    }

    //使用泛型通配符来替代
    private static int numElementsInCommon1(Set<?> s1, Set<?> s2) {
        int result = 0;
        for (Object o1 : s1){
            if (s2.contains(o1)){
                result++;
            }
        }
        return result;
    }
}
