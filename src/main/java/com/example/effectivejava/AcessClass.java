package com.example.effectivejava;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 《effective java》-15：最小化类和成员的可访问性
 * 在与我们编写的软件的对应功能保持一致的情况下，我们应该使用最低的访问级别
 * 4种访问级别：
 * - private — 成员只能被声明它的顶级类访问。
 * - package-private — 成员可以被声明它的包下面的所有类访问。从技术上讲，它被称为缺省访问权限（default access），当没有指定访问修饰符时，我们就会得到这种访问级别（接口成员除外，它们默认是公有的）。
 * - protected — 成员可以被声明它的类的子类访问（但有一些限制[JLS, 6.6.2]），同时，声明它的包下面的所有类也可以访问它。
 * - public — 成员可以在任何地方被访问。
 * 《effective java》-16：在公有类中使用访问方法，而不是公有域
 * 除了作为常量的公有静态final域，公有类不应该有公有域，应该提供公有方法来进行访问。同时还要确保被公有静态final域引用的对象是不可变的
 * @author Don
 * @date 2022/2/14.
 */
public class AcessClass {
    //让一个类具有公有静态final数组域，或者返回这种域的访问方法，这总是错的
    private static final String[] PRIVATE_VALUES = {"1", "2"};
    //将PRIVATE_VALUES设为私有并返回一个不可变数组
    public static final List<String> VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
    //静态常量可以作为公有域暴露
    public static final int HOURS_PER_DAY = 24;
    //以公共方法来提供操作，不要以域来提供
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
