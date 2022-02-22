package com.example.effectivejava;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 《effective java》-27：消除不需要检查的警告
 * 尽可能地消除每一个未经检查的编译期警告，包括阿里规范插件提示
 * 两种方式消除：
 * 1、修改代码使得满足规范
 * 2、对于无法满足但是确保正确的，加@SuppressWarnings注解
 * SuppressWarnings 注解可用于任何声明，从单个局部变量声明到整个类
 * 每当使用 @SuppressWarnings(“unchecked”) 注解时，请添加注释，说明为什么是安全的
 * @author Don
 * @date 2022/2/22.
 */
public class EliminateWarnClass {

    //这里HashSet会提示Raw use of parameterized class 'HashSet'
    public void warnCode(){
        Set<String> exaltation = new HashSet();
        //加钻石符号<>来消除
        Set<String> exaltation2 = new HashSet<>();
    }

    public <T extends String> T[] toArray(T[] a) {
        String[] elements = new String[]{"1","2"};
        int size = 10;
        if (a.length < size) {
            //添加注释，说明为什么是安全的
            // This cast is correct because the array we're creating
            // is of the same type as the one passed in, which is T[].
            @SuppressWarnings("unchecked") T[] result = (T[]) Arrays.copyOf(elements, size, a.getClass());
            return result;
        }
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size){
             a[size] = null; return a;
        }
        return null;
    }
}
