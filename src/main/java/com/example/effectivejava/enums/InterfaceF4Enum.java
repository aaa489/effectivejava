package com.example.effectivejava.enums;

/**
 * 《effective java》-38：使用接口模拟可扩展的枚举
 * 虽然不能编写可扩展的枚举类型，但是你可以编写一个接口来配合实现接口的基本的枚举类型，来对它进行模拟
 * @author Don
 * @date 2022/2/24.
 */
public class InterfaceF4Enum {

    //定义可扩展的枚举操作接口
    public interface Operation {
        double apply(double x, double y);
    }

     /**
       * 做了加减乘除操作
       * @author: Don
       * @date: 2022/2/24 10:48
       **/
    public enum BasicOperation implements Operation {
        PLUS("+") {
            public double apply(double x, double y) { return x + y; }
            },
        MINUS("-") {
            public double apply(double x, double y) { return x - y; }
            },
        TIMES("*") {
            public double apply(double x, double y) { return x * y; }
            },
        DIVIDE("/") {
            public double apply(double x, double y) { return x / y; }
        };
        private final String symbol;
        BasicOperation(String symbol) {
            this.symbol = symbol;
        }
        @Override public String toString() {
            return symbol;
        }
    }

     /**
       * 扩展了除模和异或运算
       * @author: Don
       * @date: 2022/2/24 10:49
       **/
    public enum ExtendedOperation implements Operation {
        EXP("^") {
            public double apply(double x, double y) {
                return Math.pow(x, y);
            }
            },
        REMAINDER("%") {
            public double apply(double x, double y) { return x % y; }
        };
        private final String symbol; ExtendedOperation(String symbol) {
            this.symbol = symbol;
        }
        @Override public String toString() {
            return symbol;
        }
    }
}
