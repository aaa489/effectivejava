package com.example.effectivejava;

/**
 * 《effective java》-23：类层次结构优于标签类
 * 类层次结构指的就是继承，所谓标签类就是类中定义了一个标签，通过标签实现不同功能
 * 标签类缺点：冗余、效率低、使用容易出错
 * 有层次的类有点：代码简单明了、运行效率高、灵活好拓展
 * @author Don
 * @date 2022/2/21.
 */
public class TagClass {

     /**
       * 引自原文的标签类
       * @author: Don
       * @date: 2022/2/21 16:19
       **/
    public static class FigureTag {
        enum Shape { RECTANGLE, CIRCLE };
        // Tag field - the shape of this figure
        final Shape shape;
        // These fields are used only if shape is RECTANGLE
        double length;
        double width;
        // This field is used only if shape is CIRCLE
        double radius;
        // Constructor for circle
        FigureTag(double radius) {
            shape = Shape.CIRCLE; this.radius = radius;
        }
        // Constructor for rectangle
        FigureTag(double length, double width) {
            shape = Shape.RECTANGLE; this.length = length; this.width = width;
        }
        double area() {
            switch(shape) {
                case RECTANGLE: return length * width;
                case CIRCLE: return Math.PI * (radius * radius);
                default: throw new AssertionError(shape);
            }
        }
    }

     /**
       * 引自原文的改造之后的有层次的类，使得功能更加内聚和单一，更方便拓展了
       * @author: Don
       * @date: 2022/2/21 16:22
       **/
    // Class hierarchy replacement for a tagged class
    public static abstract class Figure {
        abstract double area();
    }

    public static class Circle extends Figure {
        final double radius;
        Circle(double radius) {
            this.radius = radius;
        }
        @Override double area() {
            return Math.PI * (radius * radius);
        }
    }

    public static class Rectangle extends Figure {
        final double length;
        final double width; Rectangle(double length, double width) {
            this.length = length;
            this.width = width;
        }
        @Override double area() {
            return length * width;
        }
    }
}
