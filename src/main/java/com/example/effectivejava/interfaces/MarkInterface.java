package com.example.effectivejava.interfaces;

/**
 * 《effective java》-41：使用标记接口定义类型
 * 相比标记注解的优点：
 * 1、标记接口定义了一个由标记类实例实现的类型；标记注解则不会
 * 2、标记接口对于标记注解的另一个优点是可以更精确地定位目标
 * 如果要标记除类和接口以外的程序元素，或者将标记符合到已经大量使用注解类型的框架中，那么标记注解是正确的选择
 * 如果发现自己正在编写目标为 ElementType.TYPE 的标记注解类型，请花点时间确定它是否应该是注释类型，是不是标记接口是否更合适
 * 什么是标记接口？比如Serializable、Cloneable等没有任何方法定义的接口
 * @author Don
 * @date 2022/2/25.
 */
public interface MarkInterface {

}
