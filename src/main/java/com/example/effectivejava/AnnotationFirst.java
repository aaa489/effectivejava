package com.example.effectivejava;

import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 《effective java》-39：注解优于命名模式
 * 注解可以在编译和执行时做强制约束，而命名是脆弱的
 * 当可以使用注解代替时，没有理由使用命名模式
 * 除了特定的开发者（toolsmith）之外，大多数程序员都不需要定义注解类型。 但所有程序员都应该
 * 使用 Java 提供的预定义注解类型（条目 40，27）。 另外，请考虑使用 IDE 或静态分析工具提供的注解
 * 这些注解可以提高这些工具提供的诊断信息的质量
 * @author Don
 * @date 2022/2/24.
 */
public class AnnotationFirst {

     /**
       * 单值版本
       * @author: Don
       * @date: 2022/2/24 11:13
       **/
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    //添加@Repeatable注解可以重复应用与单个元素而不数组
    @Repeatable(ExceptionTestContainer.class)
    public @interface ExceptionTest {
        Class<? extends Throwable> value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ExceptionTestContainer {
        ExceptionTest[] value();
    }

     /**
       * 数组版本
       * @author: Don
       * @date: 2022/2/24 11:13
       **/
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ExceptionTestArray {
        Class<? extends Exception>[] value();
    }

    //单值版本使用
    @ExceptionTest(ArithmeticException.class)
    //加了@Repeatable之后可以重复添加
    @ExceptionTest(NullPointerException.class)
    public static void m1() {
        // Test should pass
        int i = 0;
        i = i / i;
    }

    //数组版本使用
    @ExceptionTestArray({ IndexOutOfBoundsException.class,
            NullPointerException.class })
    public static void doublyBad() {
        List<String> list = new ArrayList<>();
        // The spec permits this method to throw either
        // IndexOutOfBoundsException or NullPointerException
        list.addAll(5, null);
    }

    public static void main(String[] args) throws Exception {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Class.forName(args[0]);
        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(ExceptionTest.class)) {
                tests++;
                try {
                    m.invoke(null); passed++;
                }
                catch (InvocationTargetException wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    System.out.println(m + " failed: " + exc);
                }
                catch (Exception exc) {
                    System.out.println("Invalid @Test: " + m);
                }
            }
        }
        System.out.printf("Passed: %d, Failed: %d%n", passed, tests - passed);
    }
}
