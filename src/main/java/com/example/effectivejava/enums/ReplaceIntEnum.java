package com.example.effectivejava.enums;

/**
 * 《effective java》-34：使用枚举类型替代整型常量
 * 枚举提供了编译时类型的安全性
 * Java 的枚举类型是完整的类，比其他语言中的其他语言更强大，其枚举本质本上是 int 值
 * Java 枚举类型背后的基本思想很简单：它们是通过公共静态 final 属性为每个枚举常量导出一个实例的类。由于没有可访问的构造方法，枚举类型实际上是 final 的
 * 如果一个枚举是广泛使用的，它应该是一个顶级类; 如果它的使用与特定的顶级类绑定，它应该是该顶级类的成员类
 * 枚举类型具有自动生成的 valueOf(String) 方法，该方法将常量名称转换为常量本身
 * @author Don
 * @date 2022/2/23.
 */
public class ReplaceIntEnum {
    //定义了int类型的值
    public static final int APPLE_FUJI = 0;
    public static final int APPLE_PIPPIN = 1;
    public static final int APPLE_GRANNY_SMITH = 2;
    public static final int ORANGE_NAVEL = 0;
    public static final int ORANGE_TEMPLE = 1;
    public static final int ORANGE_BLOOD = 2;

     /**
       * 使用枚举代替整型
       * @author: Don
       * @date: 2022/2/23 13:59
       **/
    public enum Apple {
        FUJI, PIPPIN, GRANNY_SMITH
    }
    public enum Orange {
        NAVEL, TEMPLE, BLOOD
    }

    //枚举本质上是不变的，所以所有的属性都应该是 final 的
    public enum Planet {
        MERCURY(3.302e+23, 2.439e6),
        VENUS(4.869e+24, 6.052e6),
        EARTH(5.975e+24, 6.378e6),
        MARS(6.419e+23, 3.393e6),
        JUPITER(1.899e+27, 7.149e7),
        SATURN(5.685e+26, 6.027e7),
        URANUS(8.683e+25, 2.556e7),
        NEPTUNE(1.024e+26, 2.477e7);
        private final double mass; // In kilograms
        private final double radius; // In meters
        private final double surfaceGravity; // In m / s^2 // Universal gravitational constant in m^3 / kg s^2
        private static final double G = 6.67300E-11; // Constructor

        Planet(double mass, double radius) {
            this.mass = mass;
            this.radius = radius;
            surfaceGravity = G * mass / (radius * radius);
        }

        public double mass() {
            return mass;
        }

        public double radius() {
            return radius;
        }

        public double surfaceGravity() {
            return surfaceGravity;
        }

        public double surfaceWeight(double mass) {
            return mass * surfaceGravity; // F = ma
        }
    }

     /**
       * 枚举中进行操作
       * Operation1是更优雅的实现，将不同的行为与每个枚举常量关联起来
       * @author: Don
       * @date: 2022/2/23 14:43
       **/
    public enum Operation {
        PLUS, MINUS, TIMES, DIVIDE;
        // Do the arithmetic operation represented by this constant
        public double apply(double x, double y) {
            switch(this) {
                case PLUS: return x + y;
                case MINUS: return x - y;
                case TIMES: return x * y;
                case DIVIDE: return x / y;
            }
            throw new AssertionError("Unknown op: " + this);
        }
    }

    public enum Operation1 {
        PLUS {public double apply(double x, double y){return x + y;}},
        MINUS {public double apply(double x, double y){return x - y;}},
        TIMES {public double apply(double x, double y){return x * y;}},
        DIVIDE{public double apply(double x, double y){return x / y;}};
        public abstract double apply(double x, double y);
    }

     /**
       * 《effective java》-35：使用实例属性替代序数
       * 所有枚举都有一个 ordinal 方法，它返回每个枚举常量类型的数值位置，我们要避免使用
       * 永远不要从枚举的序号中得出与它相关的值; 请将其保存在实例属性中
       * @author: Don
       * @date: 2022/2/23 14:56
       **/
    public enum Ensemble {
        SOLO, DUET, TRIO, QUARTET, QUINTET, SEXTET, SEPTET, OCTET, NONET, DECTET;
        public int numberOfMusicians() {
            return ordinal() + 1;
        }
    }

    //Ensemble1的设计更灵活
    public enum Ensemble1 {
        SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
        SEXTET(6), SEPTET(7), OCTET(8), DOUBLE_QUARTET(8),
        NONET(9), DECTET(10), TRIPLE_QUARTET(12);
        private final int numberOfMusicians;
        Ensemble1(int size) {
            this.numberOfMusicians = size;
        }
        public int numberOfMusicians() {
            return numberOfMusicians;
        }
    }
}
