package com.example.effectivejava;

/**
 * 《effective java》-51：仔细设计方法签名
 * 1、名称应始终遵守标准命名约定定(条目 68)，与同一包中的其他名称一致且易于理解，避免使用较长的方法名
 * 2、不要过分地提供方便的方法，太多的方法使得类难以学习、使用、文档化、测试和维护
 * 3、避免过长的参数列表，目标是四个或更少的参数，相同类型参数的长序列尤其有害（如 String，String，String，String，String）
 * 三种方案避免长参数列表：
 * 1）一种方法是将方法分解为多个方法
 * 2）创建辅助类来保存参数组
 * 3）采用 Builder 模式
 * 4、对于参数类型，优先选择接口而不是类
 * 5、与布尔型参数相比，优先使用两个元素枚举类型，除非布尔型参数的含义在方法名中是明确的
 * @author Don
 * @date 2022/3/1.
 */
public class DesignMethodClass {

}