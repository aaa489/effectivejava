package com.example.effectivejava.interfaces;

/**
 * 《effective java》-22：接口仅用来定义类型
 * 1、接口应该用来定义实际使用时的类型，可以进行引用
 * 2、不要定义常量接口（只包含常量，不包含任何方法的），是不规范的
 * 3、如果你想导出常量，有几个合理的选择方案
 * 1）如果常量与现有的类或接口紧密相关，则应将其添加到该类或接口中
 * 2）如果常量最好被看作枚举类型的成员，则应该使用枚举类型（条目 34）导出它们
 * 3）否则，你应该用一个不可实例化的工具类来导出常量（条目 4），在使用的地方直接import进来
 * 比如下面这个例子在其他地方使用就是import static com.example.effectivejava.interfaces.PhysicalConstants.PhysicalConstantsClass.*;
 * @author Don
 * @date 2022/2/21.
 */
public interface PhysicalConstants {
    //这几个都是不规范的常量定义
    // Avogadro's number (1/mol)
    static final double AVOGADROS_NUMBER = 6.022_140_857e23;
    // Boltzmann constant (J/K)
    static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;
    // Mass of the electron (kg)
    static final double ELECTRON_MASS = 9.109_383_56e-31;

     /**
       * 不可实例化工具类
       * @author: Don
       * @date: 2022/2/21 15:49
       **/
    public class PhysicalConstantsClass {
        private PhysicalConstantsClass() { }
        // Prevents instantiation
        public static final double AVOGADROS_NUMBER = 6.022_140_857e23;
        public static final double BOLTZMANN_CONST = 1.380_648_52e-23;
        public static final double ELECTRON_MASS = 9.109_383_56e-31;
    }
}
