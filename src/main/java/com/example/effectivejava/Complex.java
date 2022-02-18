package com.example.effectivejava;

/**
 * 《effective java》-17：使可变性最小化
 * 如果一个类不能做成不可变，那就尽可能限制它的可变性
 * 要让一个类不可变，我们可以遵循以下5条规则：
 * 1、不要提供能修改对象状态的方法（即setter方法）
 * 2、确保这个类不能被扩展（将类设为final、还可以将类的所有构造器都私有化或者包级私有化，同时增加公有静态工厂来替代公有构造方法）
 * 3、将所有域设为final
 * 4、将所有域设为私有
 * 5、确保对任何可变组件的互斥访问，不要返回对象引用
 * 优点：线程安全、可以自由地被共享、不可变类可以提供静态工厂（条目1）将频繁被请求的实例缓存起来，从而避免重复创建现有实例、
 * 缺点：对于每个不同的值都需要一个对应的对象
 * @author Don
 * @date 2022/2/16.
 */
public class Complex {
    private final double re;
    private final double im;
    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }
    public double realPart() {
        return re;
    }
    public double imaginaryPart() {
        return im;
    }
    public Complex plus(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }
    public Complex minus(Complex c) {
        return new Complex(re - c.re, im - c.im);
    }
    public Complex times(Complex c) {
        return new Complex(re * c.re - im * c.im, re * c.im + im * c.re);
    }
    public Complex dividedBy(Complex c) {
        double tmp = c.re * c.re + c.im * c.im;
        return new Complex((re * c.re + im * c.im) / tmp, (im * c.re - re * c.im) / tmp);

    }
    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }
        if (!(o instanceof Complex)){
            return false;
        }
        Complex c = (Complex) o;
        // See page 47 to find out why we use compare instead of ==
        return Double.compare(c.re, re) == 0 && Double.compare(c.im, im) == 0;
    }
    @Override
    public int hashCode() {
        return 31 * Double.hashCode(re) + Double.hashCode(im);
    }
    @Override
    public String toString() {
        return "(" + re + " + " + im + "i)";
    }
}
